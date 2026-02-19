package org.kxml2.wap;

import com.streamax.config.constant.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import kotlin.text.Typography;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class WbxmlParser implements XmlPullParser {
    static final String HEX_DIGITS = "0123456789abcdef";
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    public static final int WAP_EXTENSION = 64;
    private int ATTR_START_TABLE = 1;
    private int ATTR_VALUE_TABLE = 2;
    private int TAG_TABLE = 0;
    private String[] attrStartTable;
    private String[] attrValueTable;
    private int attributeCount;
    private String[] attributes = new String[16];
    private Hashtable cacheStringTable = null;
    private boolean degenerated;
    private int depth;
    private String[] elementStack = new String[16];
    private String encoding;
    private InputStream in;
    private boolean isWhitespace;
    private String name;
    private String namespace;
    private int nextId = -2;
    private int[] nspCounts = new int[4];
    private String[] nspStack = new String[8];
    private String prefix;
    private boolean processNsp;
    private int publicIdentifierId;
    private byte[] stringTable;
    private Vector tables = new Vector();
    private String[] tagTable;
    private String text;
    private int type;
    private int version;
    private int wapCode;
    private Object wapExtensionData;

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
    }

    public String getAttributeType(int i) {
        return "CDATA";
    }

    public int getColumnNumber() {
        return -1;
    }

    public int getLineNumber() {
        return -1;
    }

    public Object getProperty(String str) {
        return null;
    }

    public boolean isAttributeDefault(int i) {
        return false;
    }

    public boolean getFeature(String str) {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            return this.processNsp;
        }
        return false;
    }

    public String getInputEncoding() {
        return this.encoding;
    }

    public int getNamespaceCount(int i) {
        if (i <= this.depth) {
            return this.nspCounts[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getNamespacePrefix(int i) {
        return this.nspStack[i << 1];
    }

    public String getNamespaceUri(int i) {
        return this.nspStack[(i << 1) + 1];
    }

    public String getNamespace(String str) {
        if ("xml".equals(str)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(str)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int namespaceCount = (getNamespaceCount(this.depth) << 1) - 2; namespaceCount >= 0; namespaceCount -= 2) {
            if (str == null) {
                String[] strArr = this.nspStack;
                if (strArr[namespaceCount] == null) {
                    return strArr[namespaceCount + 1];
                }
            } else if (str.equals(this.nspStack[namespaceCount])) {
                return this.nspStack[namespaceCount + 1];
            }
        }
        return null;
    }

    public int getDepth() {
        return this.depth;
    }

    public String getPositionDescription() {
        StringBuffer stringBuffer = new StringBuffer(this.type < TYPES.length ? TYPES[this.type] : "unknown");
        stringBuffer.append(' ');
        int i = this.type;
        if (i == 2 || i == 3) {
            if (this.degenerated) {
                stringBuffer.append("(empty) ");
            }
            stringBuffer.append(Typography.less);
            if (this.type == 3) {
                stringBuffer.append('/');
            }
            if (this.prefix != null) {
                stringBuffer.append("{" + this.namespace + Constants.JsonSstringSuffix + this.prefix + ":");
            }
            stringBuffer.append(this.name);
            int i2 = this.attributeCount << 2;
            for (int i3 = 0; i3 < i2; i3 += 4) {
                stringBuffer.append(' ');
                int i4 = i3 + 1;
                if (this.attributes[i4] != null) {
                    stringBuffer.append("{" + this.attributes[i3] + Constants.JsonSstringSuffix + this.attributes[i4] + ":");
                }
                stringBuffer.append(this.attributes[i3 + 2] + "='" + this.attributes[i3 + 3] + "'");
            }
            stringBuffer.append(Typography.greater);
        } else if (i != 7) {
            if (i != 4) {
                stringBuffer.append(getText());
            } else if (this.isWhitespace) {
                stringBuffer.append("(whitespace)");
            } else {
                String text2 = getText();
                if (text2.length() > 16) {
                    text2 = text2.substring(0, 16) + "...";
                }
                stringBuffer.append(text2);
            }
        }
        return stringBuffer.toString();
    }

    public boolean isWhitespace() throws XmlPullParserException {
        int i = this.type;
        if (!(i == 4 || i == 7 || i == 5)) {
            exception(ILLEGAL_TYPE);
        }
        return this.isWhitespace;
    }

    public String getText() {
        return this.text;
    }

    public char[] getTextCharacters(int[] iArr) {
        if (this.type >= 4) {
            iArr[0] = 0;
            iArr[1] = this.text.length();
            char[] cArr = new char[this.text.length()];
            String str = this.text;
            str.getChars(0, str.length(), cArr, 0);
            return cArr;
        }
        iArr[0] = -1;
        iArr[1] = -1;
        return null;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getName() {
        return this.name;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.type != 2) {
            exception(ILLEGAL_TYPE);
        }
        return this.degenerated;
    }

    public int getAttributeCount() {
        return this.attributeCount;
    }

    public String getAttributeNamespace(int i) {
        if (i < this.attributeCount) {
            return this.attributes[i << 2];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeName(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i << 2) + 2];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributePrefix(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i << 2) + 1];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeValue(int i) {
        if (i < this.attributeCount) {
            return this.attributes[(i << 2) + 3];
        }
        throw new IndexOutOfBoundsException();
    }

    public String getAttributeValue(String str, String str2) {
        for (int i = (this.attributeCount << 2) - 4; i >= 0; i -= 4) {
            if (this.attributes[i + 2].equals(str2) && (str == null || this.attributes[i].equals(str))) {
                return this.attributes[i + 3];
            }
        }
        return null;
    }

    public int getEventType() throws XmlPullParserException {
        return this.type;
    }

    public int next() throws XmlPullParserException, IOException {
        this.isWhitespace = true;
        int i = 9999;
        while (true) {
            String str = this.text;
            nextImpl();
            int i2 = this.type;
            if (i2 < i) {
                i = i2;
            }
            if (i <= 5) {
                if (i >= 4) {
                    if (str != null) {
                        if (this.text != null) {
                            str = str + this.text;
                        }
                        this.text = str;
                    }
                    int peekId = peekId();
                    if (!(peekId == 2 || peekId == 3 || peekId == 4 || peekId == 68 || peekId == 196 || peekId == 131 || peekId == 132)) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        this.type = i;
        if (i > 4) {
            this.type = 4;
        }
        return this.type;
    }

    public int nextToken() throws XmlPullParserException, IOException {
        this.isWhitespace = true;
        nextImpl();
        return this.type;
    }

    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.type == 4 && this.isWhitespace) {
            next();
        }
        int i = this.type;
        if (!(i == 3 || i == 2)) {
            exception("unexpected type");
        }
        return this.type;
    }

    public String nextText() throws XmlPullParserException, IOException {
        String str = null;
        if (this.type != 2) {
            exception("precondition: START_TAG");
        }
        next();
        if (this.type == 4) {
            str = getText();
            next();
        } else {
            str = "";
        }
        if (this.type != 3) {
            exception("END_TAG expected");
        }
        return str;
    }

    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        String str3;
        if (i != this.type || ((str != null && !str.equals(getNamespace())) || (str2 != null && !str2.equals(getName())))) {
            StringBuilder sb = new StringBuilder();
            sb.append("expected: ");
            if (i == 64) {
                str3 = "WAP Ext.";
            } else {
                str3 = TYPES[i] + " {" + str + Constants.JsonSstringSuffix + str2;
            }
            sb.append(str3);
            exception(sb.toString());
        }
    }

    public void setInput(Reader reader) throws XmlPullParserException {
        exception("InputStream required");
    }

    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        this.in = inputStream;
        try {
            this.version = readByte();
            int readInt = readInt();
            this.publicIdentifierId = readInt;
            if (readInt == 0) {
                readInt();
            }
            int readInt2 = readInt();
            if (str != null) {
                this.encoding = str;
            } else if (readInt2 == 4) {
                this.encoding = "ISO-8859-1";
            } else if (readInt2 == 106) {
                this.encoding = "UTF-8";
            } else {
                throw new UnsupportedEncodingException("" + readInt2);
            }
            int readInt3 = readInt();
            this.stringTable = new byte[readInt3];
            int i = 0;
            while (true) {
                if (i >= readInt3) {
                    break;
                }
                int read = inputStream.read(this.stringTable, i, readInt3 - i);
                if (read <= 0) {
                    break;
                }
                i += read;
            }
            selectPage(0, true);
            selectPage(0, false);
        } catch (IOException unused) {
            exception("Illegal input format");
        }
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            this.processNsp = z;
            return;
        }
        exception("unsupported feature: " + str);
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException("unsupported property: " + str);
    }

    private final boolean adjustNsp() throws XmlPullParserException {
        int i;
        String str = null;
        int i2 = 0;
        boolean z = false;
        while (true) {
            i = this.attributeCount;
            if (i2 >= (i << 2)) {
                break;
            }
            String str2 = this.attributes[i2 + 2];
            int indexOf = str2.indexOf(58);
            if (indexOf != -1) {
                String substring = str2.substring(0, indexOf);
                str = str2.substring(indexOf + 1);
                str2 = substring;
            } else if (str2.equals("xmlns")) {
                str = null;
            } else {
                i2 += 4;
            }
            if (!str2.equals("xmlns")) {
                z = true;
            } else {
                int[] iArr = this.nspCounts;
                int i3 = this.depth;
                int i4 = iArr[i3];
                iArr[i3] = i4 + 1;
                int i5 = i4 << 1;
                String[] ensureCapacity = ensureCapacity(this.nspStack, i5 + 2);
                this.nspStack = ensureCapacity;
                ensureCapacity[i5] = str;
                String[] strArr = this.attributes;
                int i6 = i2 + 3;
                ensureCapacity[i5 + 1] = strArr[i6];
                if (str != null && strArr[i6].equals("")) {
                    exception("illegal empty namespace");
                }
                String[] strArr2 = this.attributes;
                int i7 = this.attributeCount - 1;
                this.attributeCount = i7;
                System.arraycopy(strArr2, i2 + 4, strArr2, i2, (i7 << 2) - i2);
                i2 -= 4;
            }
            i2 += 4;
        }
        if (z) {
            int i8 = (i << 2) - 4;
            while (i8 >= 0) {
                int i9 = i8 + 2;
                String str3 = this.attributes[i9];
                int indexOf2 = str3.indexOf(58);
                if (indexOf2 != 0) {
                    if (indexOf2 != -1) {
                        String substring2 = str3.substring(0, indexOf2);
                        String substring3 = str3.substring(indexOf2 + 1);
                        String namespace2 = getNamespace(substring2);
                        if (namespace2 != null) {
                            String[] strArr3 = this.attributes;
                            strArr3[i8] = namespace2;
                            strArr3[i8 + 1] = substring2;
                            strArr3[i9] = substring3;
                            for (int i10 = (this.attributeCount << 2) - 4; i10 > i8; i10 -= 4) {
                                if (substring3.equals(this.attributes[i10 + 2]) && namespace2.equals(this.attributes[i10])) {
                                    exception("Duplicate Attribute: {" + namespace2 + Constants.JsonSstringSuffix + substring3);
                                }
                            }
                        } else {
                            throw new RuntimeException("Undefined Prefix: " + substring2 + " in " + this);
                        }
                    }
                    i8 -= 4;
                } else {
                    throw new RuntimeException("illegal attribute name: " + str3 + " at " + this);
                }
            }
        }
        int indexOf3 = this.name.indexOf(58);
        if (indexOf3 == 0) {
            exception("illegal tag name: " + this.name);
        } else if (indexOf3 != -1) {
            this.prefix = this.name.substring(0, indexOf3);
            this.name = this.name.substring(indexOf3 + 1);
        }
        String namespace3 = getNamespace(this.prefix);
        this.namespace = namespace3;
        if (namespace3 == null) {
            if (this.prefix != null) {
                exception("undefined prefix: " + this.prefix);
            }
            this.namespace = "";
        }
        return z;
    }

    private final void setTable(int i, int i2, String[] strArr) {
        if (this.stringTable == null) {
            while (true) {
                int i3 = i * 3;
                if (this.tables.size() < i3 + 3) {
                    this.tables.addElement((Object) null);
                } else {
                    this.tables.setElementAt(strArr, i3 + i2);
                    return;
                }
            }
        } else {
            throw new RuntimeException("setXxxTable must be called before setInput!");
        }
    }

    private final void exception(String str) throws XmlPullParserException {
        throw new XmlPullParserException(str, this, (Throwable) null);
    }

    private void selectPage(int i, boolean z) throws XmlPullParserException {
        if (this.tables.size() != 0 || i != 0) {
            int i2 = i * 3;
            if (i2 > this.tables.size()) {
                exception("Code Page " + i + " undefined!");
            }
            if (z) {
                this.tagTable = (String[]) this.tables.elementAt(i2 + this.TAG_TABLE);
                return;
            }
            this.attrStartTable = (String[]) this.tables.elementAt(this.ATTR_START_TABLE + i2);
            this.attrValueTable = (String[]) this.tables.elementAt(i2 + this.ATTR_VALUE_TABLE);
        }
    }

    private final void nextImpl() throws IOException, XmlPullParserException {
        if (this.type == 3) {
            this.depth--;
        }
        if (this.degenerated) {
            this.type = 3;
            this.degenerated = false;
            return;
        }
        this.text = null;
        this.prefix = null;
        this.name = null;
        int peekId = peekId();
        while (peekId == 0) {
            this.nextId = -2;
            selectPage(readByte(), true);
            peekId = peekId();
        }
        this.nextId = -2;
        if (peekId == -1) {
            this.type = 1;
        } else if (peekId == 1) {
            int i = (this.depth - 1) << 2;
            this.type = 3;
            String[] strArr = this.elementStack;
            this.namespace = strArr[i];
            this.prefix = strArr[i + 1];
            this.name = strArr[i + 2];
        } else if (peekId == 2) {
            this.type = 6;
            char readInt = (char) readInt();
            this.text = "" + readInt;
            this.name = "#" + readInt;
        } else if (peekId != 3) {
            switch (peekId) {
                case 64:
                case 65:
                case 66:
                    break;
                case 67:
                    throw new RuntimeException("PI curr. not supp.");
                default:
                    switch (peekId) {
                        case 128:
                        case 129:
                        case 130:
                            break;
                        case 131:
                            this.type = 4;
                            this.text = readStrT();
                            return;
                        default:
                            switch (peekId) {
                                case Wbxml.EXT_0:
                                case Wbxml.EXT_1:
                                case Wbxml.EXT_2:
                                case Wbxml.OPAQUE:
                                    break;
                                default:
                                    parseElement(peekId);
                                    return;
                            }
                    }
            }
            this.type = 64;
            this.wapCode = peekId;
            this.wapExtensionData = parseWapExtension(peekId);
        } else {
            this.type = 4;
            this.text = readStrI();
        }
    }

    public Object parseWapExtension(int i) throws IOException, XmlPullParserException {
        switch (i) {
            case 64:
            case 65:
            case 66:
                return readStrI();
            default:
                switch (i) {
                    case 128:
                    case 129:
                    case 130:
                        return new Integer(readInt());
                    default:
                        byte[] bArr = null;
                        switch (i) {
                            case Wbxml.EXT_0:
                            case Wbxml.EXT_1:
                            case Wbxml.EXT_2:
                                break;
                            case Wbxml.OPAQUE:
                                int readInt = readInt();
                                bArr = new byte[readInt];
                                int i2 = readInt;
                                while (i2 > 0) {
                                    i2 -= this.in.read(bArr, readInt - i2, i2);
                                }
                                break;
                            default:
                                exception("illegal id: " + i);
                                return null;
                        }
                        return bArr;
                }
        }
    }

    public void readAttr() throws IOException, XmlPullParserException {
        StringBuffer stringBuffer;
        int readByte = readByte();
        int i = 0;
        while (readByte != 1) {
            while (readByte == 0) {
                selectPage(readByte(), false);
                readByte = readByte();
            }
            String resolveId = resolveId(this.attrStartTable, readByte);
            int indexOf = resolveId.indexOf(61);
            if (indexOf == -1) {
                stringBuffer = new StringBuffer();
            } else {
                StringBuffer stringBuffer2 = new StringBuffer(resolveId.substring(indexOf + 1));
                resolveId = resolveId.substring(0, indexOf);
                stringBuffer = stringBuffer2;
            }
            int readByte2 = readByte();
            while (true) {
                if (readByte2 > 128 || readByte2 == 0 || readByte2 == 2 || readByte2 == 3 || readByte2 == 131 || ((readByte2 >= 64 && readByte2 <= 66) || (readByte2 >= 128 && readByte2 <= 130))) {
                    if (readByte2 == 0) {
                        selectPage(readByte(), false);
                    } else if (readByte2 == 2) {
                        stringBuffer.append((char) readInt());
                    } else if (readByte2 != 3) {
                        switch (readByte2) {
                            case 64:
                            case 65:
                            case 66:
                                stringBuffer.append(resolveWapExtension(readByte2, parseWapExtension(readByte2)));
                                break;
                            default:
                                switch (readByte2) {
                                    case 128:
                                    case 129:
                                    case 130:
                                        break;
                                    case 131:
                                        stringBuffer.append(readStrT());
                                        break;
                                    default:
                                        switch (readByte2) {
                                            case Wbxml.EXT_0:
                                            case Wbxml.EXT_1:
                                            case Wbxml.EXT_2:
                                            case Wbxml.OPAQUE:
                                                break;
                                            default:
                                                stringBuffer.append(resolveId(this.attrValueTable, readByte2));
                                                break;
                                        }
                                }
                                stringBuffer.append(resolveWapExtension(readByte2, parseWapExtension(readByte2)));
                                break;
                        }
                    } else {
                        stringBuffer.append(readStrI());
                    }
                    readByte2 = readByte();
                } else {
                    break;
                }
            }
            String[] ensureCapacity = ensureCapacity(this.attributes, i + 4);
            this.attributes = ensureCapacity;
            int i2 = i + 1;
            ensureCapacity[i] = "";
            int i3 = i2 + 1;
            ensureCapacity[i2] = null;
            int i4 = i3 + 1;
            ensureCapacity[i3] = resolveId;
            i = i4 + 1;
            ensureCapacity[i4] = stringBuffer.toString();
            this.attributeCount++;
            readByte = readByte2;
        }
    }

    private int peekId() throws IOException {
        if (this.nextId == -2) {
            this.nextId = this.in.read();
        }
        return this.nextId;
    }

    /* access modifiers changed from: protected */
    public String resolveWapExtension(int i, Object obj) {
        if (obj instanceof byte[]) {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = (byte[]) obj;
            for (int i2 = 0; i2 < bArr.length; i2++) {
                stringBuffer.append(HEX_DIGITS.charAt((bArr[i2] >> 4) & 15));
                stringBuffer.append(HEX_DIGITS.charAt(bArr[i2] & 15));
            }
            return stringBuffer.toString();
        }
        return "$(" + obj + ")";
    }

    /* access modifiers changed from: package-private */
    public String resolveId(String[] strArr, int i) throws IOException {
        int i2 = (i & 127) - 5;
        if (i2 == -1) {
            this.wapCode = -1;
            return readStrT();
        } else if (i2 < 0 || strArr == null || i2 >= strArr.length || strArr[i2] == null) {
            throw new IOException("id " + i + " undef.");
        } else {
            this.wapCode = i2 + 5;
            return strArr[i2];
        }
    }

    /* access modifiers changed from: package-private */
    public void parseElement(int i) throws IOException, XmlPullParserException {
        this.type = 2;
        this.name = resolveId(this.tagTable, i & 63);
        this.attributeCount = 0;
        if ((i & 128) != 0) {
            readAttr();
        }
        this.degenerated = (i & 64) == 0;
        int i2 = this.depth;
        this.depth = i2 + 1;
        int i3 = i2 << 2;
        String[] ensureCapacity = ensureCapacity(this.elementStack, i3 + 4);
        this.elementStack = ensureCapacity;
        ensureCapacity[i3 + 3] = this.name;
        int i4 = this.depth;
        int[] iArr = this.nspCounts;
        if (i4 >= iArr.length) {
            int[] iArr2 = new int[(i4 + 4)];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.nspCounts = iArr2;
        }
        int[] iArr3 = this.nspCounts;
        int i5 = this.depth;
        iArr3[i5] = iArr3[i5 - 1];
        for (int i6 = this.attributeCount - 1; i6 > 0; i6--) {
            for (int i7 = 0; i7 < i6; i7++) {
                if (getAttributeName(i6).equals(getAttributeName(i7))) {
                    exception("Duplicate Attribute: " + getAttributeName(i6));
                }
            }
        }
        if (this.processNsp) {
            adjustNsp();
        } else {
            this.namespace = "";
        }
        String[] strArr = this.elementStack;
        strArr[i3] = this.namespace;
        strArr[i3 + 1] = this.prefix;
        strArr[i3 + 2] = this.name;
    }

    private final String[] ensureCapacity(String[] strArr, int i) {
        if (strArr.length >= i) {
            return strArr;
        }
        String[] strArr2 = new String[(i + 16)];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    /* access modifiers changed from: package-private */
    public int readByte() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            return read;
        }
        throw new IOException(UNEXPECTED_EOF);
    }

    /* access modifiers changed from: package-private */
    public int readInt() throws IOException {
        int readByte;
        int i = 0;
        do {
            readByte = readByte();
            i = (i << 7) | (readByte & 127);
        } while ((readByte & 128) != 0);
        return i;
    }

    /* access modifiers changed from: package-private */
    public String readStrI() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = true;
        while (true) {
            int read = this.in.read();
            if (read == 0) {
                this.isWhitespace = z;
                String str = new String(byteArrayOutputStream.toByteArray(), this.encoding);
                byteArrayOutputStream.close();
                return str;
            } else if (read != -1) {
                if (read > 32) {
                    z = false;
                }
                byteArrayOutputStream.write(read);
            } else {
                throw new IOException(UNEXPECTED_EOF);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String readStrT() throws IOException {
        int readInt = readInt();
        if (this.cacheStringTable == null) {
            this.cacheStringTable = new Hashtable();
        }
        String str = (String) this.cacheStringTable.get(new Integer(readInt));
        if (str != null) {
            return str;
        }
        int i = readInt;
        while (true) {
            byte[] bArr = this.stringTable;
            if (i >= bArr.length || bArr[i] == 0) {
                String str2 = new String(this.stringTable, readInt, i - readInt, this.encoding);
                this.cacheStringTable.put(new Integer(readInt), str2);
                return str2;
            } else {
                i++;
            }
        }
    }

    public void setTagTable(int i, String[] strArr) {
        setTable(i, this.TAG_TABLE, strArr);
    }

    public void setAttrStartTable(int i, String[] strArr) {
        setTable(i, this.ATTR_START_TABLE, strArr);
    }

    public void setAttrValueTable(int i, String[] strArr) {
        setTable(i, this.ATTR_VALUE_TABLE, strArr);
    }

    public int getWapCode() {
        return this.wapCode;
    }

    public Object getWapExtensionData() {
        return this.wapExtensionData;
    }
}

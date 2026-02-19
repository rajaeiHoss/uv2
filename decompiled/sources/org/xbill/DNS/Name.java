package org.xbill.DNS;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import kotlin.UByte;
import org.kxml2.wap.Wbxml;

public class Name implements Comparable, Serializable {
    private static final int LABEL_COMPRESSION = 192;
    private static final int LABEL_MASK = 192;
    private static final int LABEL_NORMAL = 0;
    private static final int MAXLABEL = 63;
    private static final int MAXLABELS = 128;
    private static final int MAXNAME = 255;
    private static final int MAXOFFSETS = 7;
    private static final DecimalFormat byteFormat;
    public static final Name empty;
    private static final byte[] emptyLabel = {0};
    private static final byte[] lowercase = new byte[256];
    public static final Name root;
    private static final long serialVersionUID = -7257019940971525644L;
    private static final Name wild;
    private static final byte[] wildLabel = {1, 42};
    private int hashcode;
    private byte[] name;
    private long offsets;

    static {
        DecimalFormat decimalFormat = new DecimalFormat();
        byteFormat = decimalFormat;
        decimalFormat.setMinimumIntegerDigits(3);
        for (int i = 0; i < lowercase.length; i++) {
            if (i < 65 || i > 90) {
                lowercase[i] = (byte) i;
            } else {
                lowercase[i] = (byte) ((i - 65) + 97);
            }
        }
        Name name2 = new Name();
        root = name2;
        name2.appendSafe(emptyLabel, 0, 1);
        Name name3 = new Name();
        empty = name3;
        name3.name = new byte[0];
        Name name4 = new Name();
        wild = name4;
        name4.appendSafe(wildLabel, 0, 1);
    }

    private Name() {
    }

    private final void setoffset(int i, int i2) {
        if (i < 7) {
            int i3 = (7 - i) * 8;
            long j = this.offsets & (~(255 << i3));
            this.offsets = j;
            this.offsets = (((long) i2) << i3) | j;
        }
    }

    private final int offset(int i) {
        if (i == 0 && getlabels() == 0) {
            return 0;
        }
        if (i < 0 || i >= getlabels()) {
            throw new IllegalArgumentException("label out of range");
        } else if (i < 7) {
            return ((int) (this.offsets >>> ((7 - i) * 8))) & 255;
        } else {
            int offset = offset(6);
            for (int i2 = 6; i2 < i; i2++) {
                offset += this.name[offset] + 1;
            }
            return offset;
        }
    }

    private final void setlabels(int i) {
        long j = this.offsets & -256;
        this.offsets = j;
        this.offsets = j | ((long) i);
    }

    private final int getlabels() {
        return (int) (this.offsets & 255);
    }

    private static final void copy(Name name2, Name name3) {
        int i = 0;
        if (name2.offset(0) == 0) {
            name3.name = name2.name;
            name3.offsets = name2.offsets;
            return;
        }
        int offset = name2.offset(0);
        int length = name2.name.length - offset;
        int labels = name2.labels();
        byte[] bArr = new byte[length];
        name3.name = bArr;
        System.arraycopy(name2.name, offset, bArr, 0, length);
        while (i < labels && i < 7) {
            name3.setoffset(i, name2.offset(i) - offset);
            i++;
        }
        name3.setlabels(labels);
    }

    private final void append(byte[] bArr, int i, int i2) throws NameTooLongException {
        byte[] bArr2 = this.name;
        int length = bArr2 == null ? 0 : bArr2.length - offset(0);
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            byte b = bArr[i3];
            if (b <= 63) {
                int i6 = b + 1;
                i3 += i6;
                i5 += i6;
                i4++;
            } else {
                throw new IllegalStateException("invalid label");
            }
        }
        int i7 = length + i5;
        if (i7 <= 255) {
            int i8 = getlabels();
            int i9 = i8 + i2;
            if (i9 <= 128) {
                byte[] bArr3 = new byte[i7];
                if (length != 0) {
                    System.arraycopy(this.name, offset(0), bArr3, 0, length);
                }
                System.arraycopy(bArr, i, bArr3, length, i5);
                this.name = bArr3;
                for (int i10 = 0; i10 < i2; i10++) {
                    setoffset(i8 + i10, length);
                    length += bArr3[length] + 1;
                }
                setlabels(i9);
                return;
            }
            throw new IllegalStateException("too many labels");
        }
        throw new NameTooLongException();
    }

    private static TextParseException parseException(String str, String str2) {
        return new TextParseException("'" + str + "': " + str2);
    }

    private final void appendFromString(String str, byte[] bArr, int i, int i2) throws TextParseException {
        try {
            append(bArr, i, i2);
        } catch (NameTooLongException unused) {
            throw parseException(str, "Name too long");
        }
    }

    private final void appendSafe(byte[] bArr, int i, int i2) {
        try {
            append(bArr, i, i2);
        } catch (NameTooLongException unused) {
        }
    }

    public Name(String str, Name name2) throws TextParseException {
        int i;
        boolean z;
        int i2;
        String str2 = str;
        Name name3 = name2;
        if (str2.equals("")) {
            throw parseException(str2, "empty name");
        } else if (str2.equals("@")) {
            if (name3 == null) {
                copy(empty, this);
            } else {
                copy(name3, this);
            }
        } else if (str2.equals(".")) {
            copy(root, this);
        } else {
            byte[] bArr = new byte[64];
            int i3 = 0;
            boolean z2 = false;
            int i4 = -1;
            int i5 = 1;
            int i6 = 0;
            for (int i7 = 0; i7 < str.length(); i7++) {
                i2 = i5;
                byte charAt = (byte) str2.charAt(i7);
                if (z2) {
                    if (charAt >= 48 && charAt <= 57 && i3 < 3) {
                        i3++;
                        i6 = (i6 * 10) + (charAt - 48);
                        if (i6 > 255) {
                            throw parseException(str2, "bad escape");
                        } else if (i3 < 3) {
                            continue;
                        } else {
                            charAt = (byte) i6;
                        }
                    } else if (i3 > 0 && i3 < 3) {
                        throw parseException(str2, "bad escape");
                    }
                    if (i5 <= 63) {
                        i2 = i5 + 1;
                        bArr[i5] = charAt;
                        i4 = i5;
                        z2 = false;
                    } else {
                        throw parseException(str2, "label too long");
                    }
                } else {
                    if (charAt == 92) {
                        i3 = 0;
                        z2 = true;
                        i6 = 0;
                    } else if (charAt != 46) {
                        i4 = i4 == -1 ? i7 : i4;
                        if (i5 <= 63) {
                            i2 = i5 + 1;
                            bArr[i5] = charAt;
                        } else {
                            throw parseException(str2, "label too long");
                        }
                    } else if (i4 != -1) {
                        bArr[0] = (byte) (i5 - 1);
                        appendFromString(str2, bArr, 0, 1);
                        i4 = -1;
                        i5 = 1;
                        i2 = i5;
                    } else {
                        throw parseException(str2, "invalid empty label");
                    }
                }
                i5 = i2;
            }
            if (i3 > 0 && i3 < 3) {
                throw parseException(str2, "bad escape");
            } else if (!z2) {
                if (i4 == -1) {
                    z = true;
                    i = 0;
                    appendFromString(str2, emptyLabel, 0, 1);
                } else {
                    i = 0;
                    bArr[0] = (byte) (i5 - 1);
                    appendFromString(str2, bArr, 0, 1);
                    z = false;
                }
                if (name3 != null && !z) {
                    appendFromString(str2, name3.name, i, name2.getlabels());
                }
            } else {
                throw parseException(str2, "bad escape");
            }
        }
    }

    public Name(String str) throws TextParseException {
        this(str, (Name) null);
    }

    public static Name fromString(String str, Name name2) throws TextParseException {
        if (str.equals("@") && name2 != null) {
            return name2;
        }
        if (str.equals(".")) {
            return root;
        }
        return new Name(str, name2);
    }

    public static Name fromString(String str) throws TextParseException {
        return fromString(str, (Name) null);
    }

    public static Name fromConstantString(String str) {
        try {
            return fromString(str, (Name) null);
        } catch (TextParseException unused) {
            throw new IllegalArgumentException("Invalid name '" + str + "'");
        }
    }

    public Name(DNSInput dNSInput) throws WireParseException {
        byte[] bArr = new byte[64];
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            int readU8 = dNSInput.readU8();
            int i = readU8 & Wbxml.EXT_0;
            if (i != 0) {
                if (i == 192) {
                    int readU82 = dNSInput.readU8() + ((readU8 & -193) << 8);
                    if (Options.check("verbosecompression")) {
                        PrintStream printStream = System.err;
                        printStream.println("currently " + dNSInput.current() + ", pointer to " + readU82);
                    }
                    if (readU82 < dNSInput.current() - 2) {
                        if (!z2) {
                            dNSInput.save();
                            z2 = true;
                        }
                        dNSInput.jump(readU82);
                        if (Options.check("verbosecompression")) {
                            PrintStream printStream2 = System.err;
                            printStream2.println("current name '" + this + "', seeking to " + readU82);
                        }
                    } else {
                        throw new WireParseException("bad compression");
                    }
                } else {
                    throw new WireParseException("bad label type");
                }
            } else if (getlabels() >= 128) {
                throw new WireParseException("too many labels");
            } else if (readU8 == 0) {
                append(emptyLabel, 0, 1);
                z = true;
            } else {
                bArr[0] = (byte) readU8;
                dNSInput.readByteArray(bArr, 1, readU8);
                append(bArr, 0, 1);
            }
        }
        if (z2) {
            dNSInput.restore();
        }
    }

    public Name(byte[] bArr) throws IOException {
        this(new DNSInput(bArr));
    }

    public Name(Name name2, int i) {
        int labels = name2.labels();
        if (i <= labels) {
            this.name = name2.name;
            int i2 = labels - i;
            setlabels(i2);
            int i3 = 0;
            while (i3 < 7 && i3 < i2) {
                setoffset(i3, name2.offset(i3 + i));
                i3++;
            }
            return;
        }
        throw new IllegalArgumentException("attempted to remove too many labels");
    }

    public static Name concatenate(Name name2, Name name3) throws NameTooLongException {
        if (name2.isAbsolute()) {
            return name2;
        }
        Name name4 = new Name();
        copy(name2, name4);
        name4.append(name3.name, name3.offset(0), name3.getlabels());
        return name4;
    }

    public Name relativize(Name name2) {
        if (name2 == null || !subdomain(name2)) {
            return this;
        }
        Name name3 = new Name();
        copy(this, name3);
        int length = length() - name2.length();
        name3.setlabels(name3.labels() - name2.labels());
        name3.name = new byte[length];
        System.arraycopy(this.name, offset(0), name3.name, 0, length);
        return name3;
    }

    public Name wild(int i) {
        if (i >= 1) {
            try {
                Name name2 = new Name();
                copy(wild, name2);
                name2.append(this.name, offset(i), getlabels() - i);
                return name2;
            } catch (NameTooLongException unused) {
                throw new IllegalStateException("Name.wild: concatenate failed");
            }
        } else {
            throw new IllegalArgumentException("must replace 1 or more labels");
        }
    }

    public Name fromDNAME(DNAMERecord dNAMERecord) throws NameTooLongException {
        Name name2 = dNAMERecord.getName();
        Name target = dNAMERecord.getTarget();
        if (!subdomain(name2)) {
            return null;
        }
        int labels = labels() - name2.labels();
        int length = length() - name2.length();
        int i = 0;
        int offset = offset(0);
        int labels2 = target.labels();
        short length2 = target.length();
        int i2 = length + length2;
        if (i2 <= 255) {
            Name name3 = new Name();
            int i3 = labels + labels2;
            name3.setlabels(i3);
            byte[] bArr = new byte[i2];
            name3.name = bArr;
            System.arraycopy(this.name, offset, bArr, 0, length);
            System.arraycopy(target.name, 0, name3.name, length, length2);
            int i4 = 0;
            while (i < 7 && i < i3) {
                name3.setoffset(i, i4);
                i4 += name3.name[i4] + 1;
                i++;
            }
            return name3;
        }
        throw new NameTooLongException();
    }

    public boolean isWild() {
        if (labels() == 0) {
            return false;
        }
        byte[] bArr = this.name;
        if (bArr[0] == 1 && bArr[1] == 42) {
            return true;
        }
        return false;
    }

    public boolean isAbsolute() {
        if (labels() == 0) {
            return false;
        }
        byte[] bArr = this.name;
        if (bArr[bArr.length - 1] == 0) {
            return true;
        }
        return false;
    }

    public short length() {
        if (getlabels() == 0) {
            return 0;
        }
        return (short) (this.name.length - offset(0));
    }

    public int labels() {
        return getlabels();
    }

    public boolean subdomain(Name name2) {
        int labels = labels();
        int labels2 = name2.labels();
        if (labels2 > labels) {
            return false;
        }
        if (labels2 == labels) {
            return equals(name2);
        }
        return name2.equals(this.name, offset(labels - labels2));
    }

    private String byteString(byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = i + 1;
        byte b = bArr[i];
        for (int i3 = i2; i3 < i2 + b; i3++) {
            int b2 = bArr[i3] & UByte.MAX_VALUE;
            if (b2 <= 32 || b2 >= Byte.MAX_VALUE) {
                stringBuffer.append('\\');
                stringBuffer.append(byteFormat.format((long) b2));
            } else if (b2 == 34 || b2 == 40 || b2 == 41 || b2 == 46 || b2 == 59 || b2 == 92 || b2 == 64 || b2 == 36) {
                stringBuffer.append('\\');
                stringBuffer.append((char) b2);
            } else {
                stringBuffer.append((char) b2);
            }
        }
        return stringBuffer.toString();
    }

    public String toString() {
        int labels = labels();
        if (labels == 0) {
            return "@";
        }
        int i = 0;
        if (labels == 1 && this.name[offset(0)] == 0) {
            return ".";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int offset = offset(0);
        while (i < labels) {
            byte[] bArr = this.name;
            byte b = bArr[offset];
            if (b > 63) {
                throw new IllegalStateException("invalid label");
            } else if (b == 0) {
                break;
            } else {
                stringBuffer.append(byteString(bArr, offset));
                stringBuffer.append('.');
                offset += b + 1;
                i++;
            }
        }
        if (!isAbsolute()) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public byte[] getLabel(int i) {
        int offset = offset(i);
        byte[] bArr = this.name;
        int i2 = (byte) (bArr[offset] + 1);
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, offset, bArr2, 0, i2);
        return bArr2;
    }

    public String getLabelString(int i) {
        return byteString(this.name, offset(i));
    }

    public void toWire(DNSOutput dNSOutput, Compression compression) {
        Name name2;
        if (isAbsolute()) {
            int labels = labels();
            for (int i = 0; i < labels - 1; i++) {
                if (i == 0) {
                    name2 = this;
                } else {
                    name2 = new Name(this, i);
                }
                int i2 = -1;
                if (compression != null) {
                    i2 = compression.get(name2);
                }
                if (i2 >= 0) {
                    dNSOutput.writeU16(49152 | i2);
                    return;
                }
                if (compression != null) {
                    compression.add(dNSOutput.current(), name2);
                }
                int offset = offset(i);
                byte[] bArr = this.name;
                dNSOutput.writeByteArray(bArr, offset, bArr[offset] + 1);
            }
            dNSOutput.writeU8(0);
            return;
        }
        throw new IllegalArgumentException("toWire() called on non-absolute name");
    }

    public byte[] toWire() {
        DNSOutput dNSOutput = new DNSOutput();
        toWire(dNSOutput, (Compression) null);
        return dNSOutput.toByteArray();
    }

    public void toWireCanonical(DNSOutput dNSOutput) {
        dNSOutput.writeByteArray(toWireCanonical());
    }

    public byte[] toWireCanonical() {
        int labels = labels();
        if (labels == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(this.name.length - offset(0))];
        int offset = offset(0);
        int i = 0;
        int i2 = 0;
        while (i < labels) {
            byte[] bArr2 = this.name;
            byte b = bArr2[offset];
            if (b <= 63) {
                bArr[i2] = bArr2[offset];
                i2++;
                offset++;
                int i3 = 0;
                while (i3 < b) {
                    bArr[i2] = lowercase[this.name[offset] & UByte.MAX_VALUE];
                    i3++;
                    i2++;
                    offset++;
                }
                i++;
            } else {
                throw new IllegalStateException("invalid label");
            }
        }
        return bArr;
    }

    public void toWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        if (z) {
            toWireCanonical(dNSOutput);
        } else {
            toWire(dNSOutput, compression);
        }
    }

    private final boolean equals(byte[] bArr, int i) {
        int labels = labels();
        int offset = offset(0);
        int i2 = 0;
        while (i2 < labels) {
            byte[] bArr2 = this.name;
            if (bArr2[offset] != bArr[i]) {
                return false;
            }
            int i3 = offset + 1;
            byte b = bArr2[offset];
            i++;
            if (b <= 63) {
                int i4 = 0;
                while (i4 < b) {
                    byte[] bArr3 = lowercase;
                    int i5 = i3 + 1;
                    int i6 = i + 1;
                    if (bArr3[this.name[i3] & UByte.MAX_VALUE] != bArr3[bArr[i] & UByte.MAX_VALUE]) {
                        return false;
                    }
                    i4++;
                    i = i6;
                    i3 = i5;
                }
                i2++;
                offset = i3;
            } else {
                throw new IllegalStateException("invalid label");
            }
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof Name)) {
            return false;
        }
        Name name2 = (Name) obj;
        if (name2.hashcode == 0) {
            name2.hashCode();
        }
        if (this.hashcode == 0) {
            hashCode();
        }
        if (name2.hashcode == this.hashcode && name2.labels() == labels()) {
            return equals(name2.name, name2.offset(0));
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashcode;
        if (i != 0) {
            return i;
        }
        int i2 = 0;
        int offset = offset(0);
        while (true) {
            byte[] bArr = this.name;
            if (offset < bArr.length) {
                i2 += (i2 << 3) + lowercase[bArr[offset] & UByte.MAX_VALUE];
                offset++;
            } else {
                this.hashcode = i2;
                return i2;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r18v0, types: [java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int compareTo(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            org.xbill.DNS.Name r1 = (org.xbill.DNS.Name) r1
            r2 = 0
            if (r0 != r1) goto L_0x000a
            return r2
        L_0x000a:
            int r3 = r17.labels()
            int r4 = r1.labels()
            if (r3 <= r4) goto L_0x0016
            r5 = r4
            goto L_0x0017
        L_0x0016:
            r5 = r3
        L_0x0017:
            r6 = 1
            r7 = 1
        L_0x0019:
            if (r7 > r5) goto L_0x005b
            int r8 = r3 - r7
            int r8 = r0.offset(r8)
            int r9 = r4 - r7
            int r9 = r1.offset(r9)
            byte[] r10 = r0.name
            byte r10 = r10[r8]
            byte[] r11 = r1.name
            byte r11 = r11[r9]
            r12 = 0
        L_0x0030:
            if (r12 >= r10) goto L_0x0054
            if (r12 >= r11) goto L_0x0054
            byte[] r13 = lowercase
            byte[] r14 = r0.name
            int r15 = r12 + r8
            int r15 = r15 + r6
            byte r14 = r14[r15]
            r14 = r14 & 255(0xff, float:3.57E-43)
            byte r14 = r13[r14]
            byte[] r15 = r1.name
            int r16 = r12 + r9
            int r16 = r16 + 1
            byte r15 = r15[r16]
            r15 = r15 & 255(0xff, float:3.57E-43)
            byte r13 = r13[r15]
            int r14 = r14 - r13
            if (r14 == 0) goto L_0x0051
            return r14
        L_0x0051:
            int r12 = r12 + 1
            goto L_0x0030
        L_0x0054:
            if (r10 == r11) goto L_0x0058
            int r10 = r10 - r11
            return r10
        L_0x0058:
            int r7 = r7 + 1
            goto L_0x0019
        L_0x005b:
            int r3 = r3 - r4
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.Name.compareTo(java.lang.Object):int");
    }
}

package org.kobjects.xmlrpc;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import org.jivesoftware.smackx.FormField;
import org.kobjects.Base64.Base64;
import org.kobjects.isodate.IsoDate;
import org.kobjects.xml.XmlReader;

public class XmlRpcParser {
    private XmlReader parser = null;

    public XmlRpcParser(XmlReader xmlReader) {
        this.parser = xmlReader;
    }

    private final Hashtable parseStruct() throws IOException {
        Hashtable hashtable = new Hashtable();
        int nextTag = nextTag();
        while (nextTag != 3) {
            nextTag();
            String nextText = nextText();
            nextTag();
            hashtable.put(nextText, parseValue());
            nextTag = nextTag();
        }
        nextTag();
        return hashtable;
    }

    private final Object parseValue() throws IOException {
        Object obj;
        Object num = null;
        int next = this.parser.next();
        if (next == 4) {
            String text = this.parser.getText();
            obj = text;
            next = this.parser.next();
        } else {
            obj = null;
        }
        if (next == 2) {
            String name = this.parser.getName();
            if (name.equals("array")) {
                obj = parseArray();
            } else if (name.equals("struct")) {
                obj = parseStruct();
            } else {
                if (name.equals("string")) {
                    num = nextText();
                } else if (name.equals("i4") || name.equals("int")) {
                    num = new Integer(Integer.parseInt(nextText().trim()));
                } else if (name.equals(FormField.TYPE_BOOLEAN)) {
                    num = new Boolean(nextText().trim().equals("1"));
                } else if (name.equals("dateTime.iso8601")) {
                    num = IsoDate.stringToDate(nextText(), 3);
                } else if (name.equals("Base64")) {
                    num = Base64.decode(nextText());
                } else {
                    if (name.equals("double")) {
                        num = nextText();
                    }
                    nextTag();
                }
                obj = num;
                nextTag();
            }
        }
        nextTag();
        return obj;
    }

    private final Vector parseArray() throws IOException {
        nextTag();
        int nextTag = nextTag();
        Vector vector = new Vector();
        while (nextTag != 3) {
            vector.addElement(parseValue());
            nextTag = this.parser.getType();
        }
        nextTag();
        nextTag();
        return vector;
    }

    private final Object parseFault() throws IOException {
        nextTag();
        Object parseValue = parseValue();
        nextTag();
        return parseValue;
    }

    private final Object parseParams() throws IOException {
        Vector vector = new Vector();
        int nextTag = nextTag();
        while (nextTag != 3) {
            nextTag();
            vector.addElement(parseValue());
            nextTag = nextTag();
        }
        nextTag();
        return vector;
    }

    public final Object parseResponse() throws IOException {
        nextTag();
        if (nextTag() == 2) {
            if ("fault".equals(this.parser.getName())) {
                return parseFault();
            }
            if ("params".equals(this.parser.getName())) {
                return parseParams();
            }
        }
        return null;
    }

    private final int nextTag() throws IOException {
        this.parser.getType();
        int next = this.parser.next();
        if (next == 4 && this.parser.isWhitespace()) {
            next = this.parser.next();
        }
        if (next == 3 || next == 2) {
            return next;
        }
        throw new IOException("unexpected type: " + next);
    }

    private final String nextText() throws IOException {
        String str;
        if (this.parser.getType() == 2) {
            int next = this.parser.next();
            if (next == 4) {
                String text = this.parser.getText();
                str = text;
                next = this.parser.next();
            } else {
                str = "";
            }
            if (next == 3) {
                return str;
            }
            throw new IOException("END_TAG expected");
        }
        throw new IOException("precondition: START_TAG");
    }
}

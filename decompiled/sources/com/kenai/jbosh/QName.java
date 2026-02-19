package com.kenai.jbosh;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class QName implements Serializable {
    private static final String emptyString = "".intern();
    private String localPart;
    private String namespaceURI;
    private String prefix;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public QName(java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = emptyString
            r1.<init>(r0, r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kenai.jbosh.QName.<init>(java.lang.String):void");
    }

    public QName(String str, String str2) {
        this(str, str2, emptyString);
    }

    public QName(String str, String str2, String str3) {
        this.namespaceURI = str == null ? emptyString : str.intern();
        if (str2 != null) {
            this.localPart = str2.intern();
            if (str3 != null) {
                this.prefix = str3.intern();
                return;
            }
            throw new IllegalArgumentException("invalid QName prefix");
        }
        throw new IllegalArgumentException("invalid QName local part");
    }

    public String getNamespaceURI() {
        return this.namespaceURI;
    }

    public String getLocalPart() {
        return this.localPart;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String toString() {
        if (this.namespaceURI == emptyString) {
            return this.localPart;
        }
        return '{' + this.namespaceURI + '}' + this.localPart;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof QName)) {
            return false;
        }
        QName qName = (QName) obj;
        return this.namespaceURI == qName.namespaceURI && this.localPart == qName.localPart;
    }

    public static QName valueOf(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("invalid QName literal");
        } else if (str.charAt(0) != '{') {
            return new QName(str);
        } else {
            int indexOf = str.indexOf(125);
            if (indexOf == -1) {
                throw new IllegalArgumentException("invalid QName literal");
            } else if (indexOf != str.length() - 1) {
                return new QName(str.substring(1, indexOf), str.substring(indexOf + 1));
            } else {
                throw new IllegalArgumentException("invalid QName literal");
            }
        }
    }

    public final int hashCode() {
        return this.namespaceURI.hashCode() ^ this.localPart.hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.namespaceURI = this.namespaceURI.intern();
        this.localPart = this.localPart.intern();
        this.prefix = this.prefix.intern();
    }
}

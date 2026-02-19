package org.xbill.DNS;

import java.io.IOException;

public class NAPTRRecord extends Record {
    private static final long serialVersionUID = 5191232392044947002L;
    private byte[] flags;
    private int order;
    private int preference;
    private byte[] regexp;
    private Name replacement;
    private byte[] service;

    NAPTRRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new NAPTRRecord();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NAPTRRecord(Name name, int i, long j, int i2, int i3, String str, String str2, String str3, Name name2) {
        super(name, 35, i, j);
        int i4 = i2;
        this.order = checkU16("order", i2);
        int i5 = i3;
        this.preference = checkU16("preference", i3);
        try {
            this.flags = byteArrayFromString(str);
            this.service = byteArrayFromString(str2);
            this.regexp = byteArrayFromString(str3);
            this.replacement = checkName("replacement", name2);
        } catch (TextParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.order = dNSInput.readU16();
        this.preference = dNSInput.readU16();
        this.flags = dNSInput.readCountedString();
        this.service = dNSInput.readCountedString();
        this.regexp = dNSInput.readCountedString();
        this.replacement = new Name(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.order = tokenizer.getUInt16();
        this.preference = tokenizer.getUInt16();
        try {
            this.flags = byteArrayFromString(tokenizer.getString());
            this.service = byteArrayFromString(tokenizer.getString());
            this.regexp = byteArrayFromString(tokenizer.getString());
            this.replacement = tokenizer.getName(name);
        } catch (TextParseException e) {
            throw tokenizer.exception(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.order);
        stringBuffer.append(" ");
        stringBuffer.append(this.preference);
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.flags, true));
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.service, true));
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.regexp, true));
        stringBuffer.append(" ");
        stringBuffer.append(this.replacement);
        return stringBuffer.toString();
    }

    public int getOrder() {
        return this.order;
    }

    public int getPreference() {
        return this.preference;
    }

    public String getFlags() {
        return byteArrayToString(this.flags, false);
    }

    public String getService() {
        return byteArrayToString(this.service, false);
    }

    public String getRegexp() {
        return byteArrayToString(this.regexp, false);
    }

    public Name getReplacement() {
        return this.replacement;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU16(this.order);
        dNSOutput.writeU16(this.preference);
        dNSOutput.writeCountedString(this.flags);
        dNSOutput.writeCountedString(this.service);
        dNSOutput.writeCountedString(this.regexp);
        this.replacement.toWire(dNSOutput, (Compression) null, z);
    }

    public Name getAdditionalName() {
        return this.replacement;
    }
}

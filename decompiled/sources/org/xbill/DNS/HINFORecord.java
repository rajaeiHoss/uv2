package org.xbill.DNS;

import java.io.IOException;

public class HINFORecord extends Record {
    private static final long serialVersionUID = -4732870630947452112L;
    private byte[] cpu;
    private byte[] os;

    HINFORecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new HINFORecord();
    }

    public HINFORecord(Name name, int i, long j, String str, String str2) {
        super(name, 13, i, j);
        try {
            this.cpu = byteArrayFromString(str);
            this.os = byteArrayFromString(str2);
        } catch (TextParseException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.cpu = dNSInput.readCountedString();
        this.os = dNSInput.readCountedString();
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        try {
            this.cpu = byteArrayFromString(tokenizer.getString());
            this.os = byteArrayFromString(tokenizer.getString());
        } catch (TextParseException e) {
            throw tokenizer.exception(e.getMessage());
        }
    }

    public String getCPU() {
        return byteArrayToString(this.cpu, false);
    }

    public String getOS() {
        return byteArrayToString(this.os, false);
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeCountedString(this.cpu);
        dNSOutput.writeCountedString(this.os);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(byteArrayToString(this.cpu, true));
        stringBuffer.append(" ");
        stringBuffer.append(byteArrayToString(this.os, true));
        return stringBuffer.toString();
    }
}

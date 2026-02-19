package org.xbill.DNS;

import java.io.IOException;

public class X25Record extends Record {
    private static final long serialVersionUID = 4267576252335579764L;
    private byte[] address;

    X25Record() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new X25Record();
    }

    private static final byte[] checkAndConvertAddress(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (!Character.isDigit(charAt)) {
                return null;
            }
            bArr[i] = (byte) charAt;
        }
        return bArr;
    }

    public X25Record(Name name, int i, long j, String str) {
        super(name, 19, i, j);
        byte[] checkAndConvertAddress = checkAndConvertAddress(str);
        this.address = checkAndConvertAddress;
        if (checkAndConvertAddress == null) {
            throw new IllegalArgumentException("invalid PSDN address " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.address = dNSInput.readCountedString();
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        String string = tokenizer.getString();
        byte[] checkAndConvertAddress = checkAndConvertAddress(string);
        this.address = checkAndConvertAddress;
        if (checkAndConvertAddress == null) {
            throw tokenizer.exception("invalid PSDN address " + string);
        }
    }

    public String getAddress() {
        return byteArrayToString(this.address, false);
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeCountedString(this.address);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        return byteArrayToString(this.address, true);
    }
}

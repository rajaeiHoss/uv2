package org.xbill.DNS;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.xbill.DNS.utils.Base16;

public class NSAPRecord extends Record {
    private static final long serialVersionUID = -1037209403185658593L;
    private byte[] address;

    NSAPRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new NSAPRecord();
    }

    private static final byte[] checkAndConvertAddress(String str) {
        if (!str.substring(0, 2).equalsIgnoreCase("0x")) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = false;
        int i = 0;
        for (int i2 = 2; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != '.') {
                int digit = Character.digit(charAt, 16);
                if (digit == -1) {
                    return null;
                }
                if (z) {
                    i += digit;
                    byteArrayOutputStream.write(i);
                    z = false;
                } else {
                    i = digit << 4;
                    z = true;
                }
            }
        }
        if (z) {
            return null;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public NSAPRecord(Name name, int i, long j, String str) {
        super(name, 22, i, j);
        byte[] checkAndConvertAddress = checkAndConvertAddress(str);
        this.address = checkAndConvertAddress;
        if (checkAndConvertAddress == null) {
            throw new IllegalArgumentException("invalid NSAP address " + str);
        }
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.address = dNSInput.readByteArray();
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        String string = tokenizer.getString();
        byte[] checkAndConvertAddress = checkAndConvertAddress(string);
        this.address = checkAndConvertAddress;
        if (checkAndConvertAddress == null) {
            throw tokenizer.exception("invalid NSAP address " + string);
        }
    }

    public String getAddress() {
        return byteArrayToString(this.address, false);
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeByteArray(this.address);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        return "0x" + Base16.toString(this.address);
    }
}

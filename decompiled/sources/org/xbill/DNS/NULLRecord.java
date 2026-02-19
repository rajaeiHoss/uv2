package org.xbill.DNS;

import java.io.IOException;

public class NULLRecord extends Record {
    private static final long serialVersionUID = -5796493183235216538L;
    private byte[] data;

    NULLRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new NULLRecord();
    }

    public NULLRecord(Name name, int i, long j, byte[] bArr) {
        super(name, 10, i, j);
        if (bArr.length <= 65535) {
            this.data = bArr;
            return;
        }
        throw new IllegalArgumentException("data must be <65536 bytes");
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.data = dNSInput.readByteArray();
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        throw tokenizer.exception("no defined text format for NULL records");
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        return unknownToString(this.data);
    }

    public byte[] getData() {
        return this.data;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeByteArray(this.data);
    }
}

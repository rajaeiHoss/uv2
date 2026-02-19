package org.xbill.DNS;

import java.io.IOException;
import java.net.InetAddress;

public class AAAARecord extends Record {
    private static final long serialVersionUID = -4588601512069748050L;
    private InetAddress address;

    AAAARecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new AAAARecord();
    }

    public AAAARecord(Name name, int i, long j, InetAddress inetAddress) {
        super(name, 28, i, j);
        if (Address.familyOf(inetAddress) == 2) {
            this.address = inetAddress;
            return;
        }
        throw new IllegalArgumentException("invalid IPv6 address");
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.address = InetAddress.getByAddress(dNSInput.readByteArray(16));
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.address = tokenizer.getAddress(2);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        return this.address.getHostAddress();
    }

    public InetAddress getAddress() {
        return this.address;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeByteArray(this.address.getAddress());
    }
}

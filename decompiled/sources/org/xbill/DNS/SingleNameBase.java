package org.xbill.DNS;

import java.io.IOException;

abstract class SingleNameBase extends Record {
    private static final long serialVersionUID = -18595042501413L;
    protected Name singleName;

    protected SingleNameBase() {
    }

    protected SingleNameBase(Name name, int i, int i2, long j) {
        super(name, i, i2, j);
    }

    protected SingleNameBase(Name name, int i, int i2, long j, Name name2, String str) {
        super(name, i, i2, j);
        this.singleName = checkName(str, name2);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.singleName = new Name(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.singleName = tokenizer.getName(name);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        return this.singleName.toString();
    }

    /* access modifiers changed from: protected */
    public Name getSingleName() {
        return this.singleName;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        this.singleName.toWire(dNSOutput, (Compression) null, z);
    }
}

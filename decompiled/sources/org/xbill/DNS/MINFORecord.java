package org.xbill.DNS;

import java.io.IOException;

public class MINFORecord extends Record {
    private static final long serialVersionUID = -3962147172340353796L;
    private Name errorAddress;
    private Name responsibleAddress;

    MINFORecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new MINFORecord();
    }

    public MINFORecord(Name name, int i, long j, Name name2, Name name3) {
        super(name, 14, i, j);
        this.responsibleAddress = checkName("responsibleAddress", name2);
        this.errorAddress = checkName("errorAddress", name3);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.responsibleAddress = new Name(dNSInput);
        this.errorAddress = new Name(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.responsibleAddress = tokenizer.getName(name);
        this.errorAddress = tokenizer.getName(name);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.responsibleAddress);
        stringBuffer.append(" ");
        stringBuffer.append(this.errorAddress);
        return stringBuffer.toString();
    }

    public Name getResponsibleAddress() {
        return this.responsibleAddress;
    }

    public Name getErrorAddress() {
        return this.errorAddress;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        this.responsibleAddress.toWire(dNSOutput, (Compression) null, z);
        this.errorAddress.toWire(dNSOutput, (Compression) null, z);
    }
}

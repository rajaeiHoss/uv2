package org.xbill.DNS;

import java.io.IOException;

public class SRVRecord extends Record {
    private static final long serialVersionUID = -3886460132387522052L;
    private int port;
    private int priority;
    private Name target;
    private int weight;

    SRVRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new SRVRecord();
    }

    public SRVRecord(Name name, int i, long j, int i2, int i3, int i4, Name name2) {
        super(name, 33, i, j);
        this.priority = checkU16("priority", i2);
        this.weight = checkU16("weight", i3);
        this.port = checkU16("port", i4);
        this.target = checkName("target", name2);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.priority = dNSInput.readU16();
        this.weight = dNSInput.readU16();
        this.port = dNSInput.readU16();
        this.target = new Name(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.priority = tokenizer.getUInt16();
        this.weight = tokenizer.getUInt16();
        this.port = tokenizer.getUInt16();
        this.target = tokenizer.getName(name);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.priority + " ");
        stringBuffer.append(this.weight + " ");
        stringBuffer.append(this.port + " ");
        stringBuffer.append(this.target);
        return stringBuffer.toString();
    }

    public int getPriority() {
        return this.priority;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getPort() {
        return this.port;
    }

    public Name getTarget() {
        return this.target;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU16(this.priority);
        dNSOutput.writeU16(this.weight);
        dNSOutput.writeU16(this.port);
        this.target.toWire(dNSOutput, (Compression) null, z);
    }

    public Name getAdditionalName() {
        return this.target;
    }
}

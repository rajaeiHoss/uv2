package org.xbill.DNS;

import java.io.IOException;

public class PXRecord extends Record {
    private static final long serialVersionUID = 1811540008806660667L;
    private Name map822;
    private Name mapX400;
    private int preference;

    PXRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new PXRecord();
    }

    public PXRecord(Name name, int i, long j, int i2, Name name2, Name name3) {
        super(name, 26, i, j);
        this.preference = checkU16("preference", i2);
        this.map822 = checkName("map822", name2);
        this.mapX400 = checkName("mapX400", name3);
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.preference = dNSInput.readU16();
        this.map822 = new Name(dNSInput);
        this.mapX400 = new Name(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.preference = tokenizer.getUInt16();
        this.map822 = tokenizer.getName(name);
        this.mapX400 = tokenizer.getName(name);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.preference);
        stringBuffer.append(" ");
        stringBuffer.append(this.map822);
        stringBuffer.append(" ");
        stringBuffer.append(this.mapX400);
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU16(this.preference);
        this.map822.toWire(dNSOutput, (Compression) null, z);
        this.mapX400.toWire(dNSOutput, (Compression) null, z);
    }

    public int getPreference() {
        return this.preference;
    }

    public Name getMap822() {
        return this.map822;
    }

    public Name getMapX400() {
        return this.mapX400;
    }
}

package org.xbill.DNS;

import java.io.IOException;
import org.xbill.DNS.utils.Base16;

public class DSRecord extends Record {
    public static final int SHA1_DIGEST_ID = 1;
    public static final int SHA256_DIGEST_ID = 2;
    private static final long serialVersionUID = -9001819329700081493L;
    private int alg;
    private byte[] digest;
    private int digestid;
    private int footprint;

    public static class Digest {
        public static final int SHA1 = 1;
        public static final int SHA256 = 2;

        private Digest() {
        }
    }

    DSRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new DSRecord();
    }

    public DSRecord(Name name, int i, long j, int i2, int i3, int i4, byte[] bArr) {
        super(name, 43, i, j);
        this.footprint = checkU16("footprint", i2);
        this.alg = checkU8("alg", i3);
        this.digestid = checkU8("digestid", i4);
        this.digest = bArr;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DSRecord(org.xbill.DNS.Name r10, int r11, long r12, int r14, int r15, org.xbill.DNS.DNSKEYRecord r16) {
        /*
            r9 = this;
            int r6 = r16.getAlgorithm()
            r7 = r15
            r0 = r16
            byte[] r8 = org.xbill.DNS.DNSSEC.generateDS(r0, r15)
            r0 = r9
            r1 = r10
            r2 = r11
            r3 = r12
            r5 = r14
            r0.<init>(r1, r2, r3, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.DSRecord.<init>(org.xbill.DNS.Name, int, long, int, int, org.xbill.DNS.DNSKEYRecord):void");
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.footprint = dNSInput.readU16();
        this.alg = dNSInput.readU8();
        this.digestid = dNSInput.readU8();
        this.digest = dNSInput.readByteArray();
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.footprint = tokenizer.getUInt16();
        this.alg = tokenizer.getUInt8();
        this.digestid = tokenizer.getUInt8();
        this.digest = tokenizer.getHex();
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.footprint);
        stringBuffer.append(" ");
        stringBuffer.append(this.alg);
        stringBuffer.append(" ");
        stringBuffer.append(this.digestid);
        if (this.digest != null) {
            stringBuffer.append(" ");
            stringBuffer.append(Base16.toString(this.digest));
        }
        return stringBuffer.toString();
    }

    public int getAlgorithm() {
        return this.alg;
    }

    public int getDigestID() {
        return this.digestid;
    }

    public byte[] getDigest() {
        return this.digest;
    }

    public int getFootprint() {
        return this.footprint;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU16(this.footprint);
        dNSOutput.writeU8(this.alg);
        dNSOutput.writeU8(this.digestid);
        byte[] bArr = this.digest;
        if (bArr != null) {
            dNSOutput.writeByteArray(bArr);
        }
    }
}

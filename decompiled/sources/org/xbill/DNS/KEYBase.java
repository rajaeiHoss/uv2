package org.xbill.DNS;

import java.io.IOException;
import java.security.PublicKey;
import kotlin.UByte;
import org.xbill.DNS.DNSSEC;
import org.xbill.DNS.utils.Base64;

abstract class KEYBase extends Record {
    private static final long serialVersionUID = 3469321722693285454L;
    protected int alg;
    protected int flags;
    protected int footprint = -1;
    protected byte[] key;
    protected int proto;
    protected PublicKey publicKey = null;

    protected KEYBase() {
    }

    public KEYBase(Name name, int i, int i2, long j, int i3, int i4, int i5, byte[] bArr) {
        super(name, i, i2, j);
        this.flags = checkU16("flags", i3);
        this.proto = checkU8("proto", i4);
        this.alg = checkU8("alg", i5);
        this.key = bArr;
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.flags = dNSInput.readU16();
        this.proto = dNSInput.readU8();
        this.alg = dNSInput.readU8();
        if (dNSInput.remaining() > 0) {
            this.key = dNSInput.readByteArray();
        }
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.flags);
        stringBuffer.append(" ");
        stringBuffer.append(this.proto);
        stringBuffer.append(" ");
        stringBuffer.append(this.alg);
        if (this.key != null) {
            if (Options.check("multiline")) {
                stringBuffer.append(" (\n");
                stringBuffer.append(Base64.formatString(this.key, 64, "\t", true));
                stringBuffer.append(" ; key_tag = ");
                stringBuffer.append(getFootprint());
            } else {
                stringBuffer.append(" ");
                stringBuffer.append(Base64.toString(this.key));
            }
        }
        return stringBuffer.toString();
    }

    public int getFlags() {
        return this.flags;
    }

    public int getProtocol() {
        return this.proto;
    }

    public int getAlgorithm() {
        return this.alg;
    }

    public byte[] getKey() {
        return this.key;
    }

    public int getFootprint() {
        int i;
        int b;
        int i2 = this.footprint;
        if (i2 >= 0) {
            return i2;
        }
        DNSOutput dNSOutput = new DNSOutput();
        int i3 = 0;
        rrToWire(dNSOutput, (Compression) null, false);
        byte[] byteArray = dNSOutput.toByteArray();
        if (this.alg == 1) {
            int b2 = byteArray[byteArray.length - 3] & UByte.MAX_VALUE;
            b = byteArray[byteArray.length - 2] & UByte.MAX_VALUE;
            i = b2 << 8;
        } else {
            i = 0;
            while (i3 < byteArray.length - 1) {
                i += ((byteArray[i3] & UByte.MAX_VALUE) << 8) + (byteArray[i3 + 1] & UByte.MAX_VALUE);
                i3 += 2;
            }
            if (i3 < byteArray.length) {
                i += (byteArray[i3] & UByte.MAX_VALUE) << 8;
            }
            b = (i >> 16) & UByte.MAX_VALUE;
        }
        int i4 = (i + b) & 65535;
        this.footprint = i4;
        return i4;
    }

    public PublicKey getPublicKey() throws DNSSEC.DNSSECException {
        PublicKey publicKey2 = this.publicKey;
        if (publicKey2 != null) {
            return publicKey2;
        }
        PublicKey publicKey3 = DNSSEC.toPublicKey(this);
        this.publicKey = publicKey3;
        return publicKey3;
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU16(this.flags);
        dNSOutput.writeU8(this.proto);
        dNSOutput.writeU8(this.alg);
        byte[] bArr = this.key;
        if (bArr != null) {
            dNSOutput.writeByteArray(bArr);
        }
    }
}

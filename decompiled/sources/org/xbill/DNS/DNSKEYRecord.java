package org.xbill.DNS;

import java.io.IOException;
import java.security.PublicKey;
import org.xbill.DNS.DNSSEC;

public class DNSKEYRecord extends KEYBase {
    private static final long serialVersionUID = -8679800040426675002L;

    public /* bridge */ /* synthetic */ int getAlgorithm() {
        return super.getAlgorithm();
    }

    public /* bridge */ /* synthetic */ int getFlags() {
        return super.getFlags();
    }

    public /* bridge */ /* synthetic */ int getFootprint() {
        return super.getFootprint();
    }

    public /* bridge */ /* synthetic */ byte[] getKey() {
        return super.getKey();
    }

    public /* bridge */ /* synthetic */ int getProtocol() {
        return super.getProtocol();
    }

    public /* bridge */ /* synthetic */ PublicKey getPublicKey() throws DNSSEC.DNSSECException {
        return super.getPublicKey();
    }

    public static class Protocol {
        public static final int DNSSEC = 3;

        private Protocol() {
        }
    }

    public static class Flags {
        public static final int REVOKE = 128;
        public static final int SEP_KEY = 1;
        public static final int ZONE_KEY = 256;

        private Flags() {
        }
    }

    DNSKEYRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new DNSKEYRecord();
    }

    public DNSKEYRecord(Name name, int i, long j, int i2, int i3, int i4, byte[] bArr) {
        super(name, 48, i, j, i2, i3, i4, bArr);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DNSKEYRecord(org.xbill.DNS.Name r12, int r13, long r14, int r16, int r17, int r18, java.security.PublicKey r19) throws org.xbill.DNS.DNSSEC.DNSSECException {
        /*
            r11 = this;
            r8 = r18
            r10 = r19
            byte[] r9 = org.xbill.DNS.DNSSEC.fromPublicKey(r10, r8)
            r2 = 48
            r0 = r11
            r1 = r12
            r3 = r13
            r4 = r14
            r6 = r16
            r7 = r17
            r0.<init>(r1, r2, r3, r4, r6, r7, r8, r9)
            r0.publicKey = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.DNSKEYRecord.<init>(org.xbill.DNS.Name, int, long, int, int, int, java.security.PublicKey):void");
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.flags = tokenizer.getUInt16();
        this.proto = tokenizer.getUInt8();
        String string = tokenizer.getString();
        this.alg = DNSSEC.Algorithm.value(string);
        if (this.alg >= 0) {
            this.key = tokenizer.getBase64();
            return;
        }
        throw tokenizer.exception("Invalid algorithm: " + string);
    }
}

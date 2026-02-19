package org.xbill.DNS;

import java.io.IOException;
import java.security.PublicKey;
import java.util.StringTokenizer;
import org.xbill.DNS.DNSSEC;

public class KEYRecord extends KEYBase {
    public static final int FLAG_NOAUTH = 32768;
    public static final int FLAG_NOCONF = 16384;
    public static final int FLAG_NOKEY = 49152;
    public static final int OWNER_HOST = 512;
    public static final int OWNER_USER = 0;
    public static final int OWNER_ZONE = 256;
    public static final int PROTOCOL_ANY = 255;
    public static final int PROTOCOL_DNSSEC = 3;
    public static final int PROTOCOL_EMAIL = 2;
    public static final int PROTOCOL_IPSEC = 4;
    public static final int PROTOCOL_TLS = 1;
    private static final long serialVersionUID = 6385613447571488906L;

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
        public static final int ANY = 255;
        public static final int DNSSEC = 3;
        public static final int EMAIL = 2;
        public static final int IPSEC = 4;
        public static final int NONE = 0;
        public static final int TLS = 1;
        private static Mnemonic protocols;

        private Protocol() {
        }

        static {
            Mnemonic mnemonic = new Mnemonic("KEY protocol", 2);
            protocols = mnemonic;
            mnemonic.setMaximum(255);
            protocols.setNumericAllowed(true);
            protocols.add(0, "NONE");
            protocols.add(1, "TLS");
            protocols.add(2, "EMAIL");
            protocols.add(3, "DNSSEC");
            protocols.add(4, "IPSEC");
            protocols.add(255, "ANY");
        }

        public static String string(int i) {
            return protocols.getText(i);
        }

        public static int value(String str) {
            return protocols.getValue(str);
        }
    }

    public static class Flags {
        public static final int EXTEND = 4096;
        public static final int FLAG10 = 32;
        public static final int FLAG11 = 16;
        public static final int FLAG2 = 8192;
        public static final int FLAG4 = 2048;
        public static final int FLAG5 = 1024;
        public static final int FLAG8 = 128;
        public static final int FLAG9 = 64;
        public static final int HOST = 512;
        public static final int NOAUTH = 32768;
        public static final int NOCONF = 16384;
        public static final int NOKEY = 49152;
        public static final int NTYP3 = 768;
        public static final int OWNER_MASK = 768;
        public static final int SIG0 = 0;
        public static final int SIG1 = 1;
        public static final int SIG10 = 10;
        public static final int SIG11 = 11;
        public static final int SIG12 = 12;
        public static final int SIG13 = 13;
        public static final int SIG14 = 14;
        public static final int SIG15 = 15;
        public static final int SIG2 = 2;
        public static final int SIG3 = 3;
        public static final int SIG4 = 4;
        public static final int SIG5 = 5;
        public static final int SIG6 = 6;
        public static final int SIG7 = 7;
        public static final int SIG8 = 8;
        public static final int SIG9 = 9;
        public static final int USER = 0;
        public static final int USE_MASK = 49152;
        public static final int ZONE = 256;
        private static Mnemonic flags;

        private Flags() {
        }

        static {
            Mnemonic mnemonic = new Mnemonic("KEY flags", 2);
            flags = mnemonic;
            mnemonic.setMaximum(65535);
            flags.setNumericAllowed(false);
            flags.add(16384, "NOCONF");
            flags.add(32768, "NOAUTH");
            flags.add(49152, "NOKEY");
            flags.add(8192, "FLAG2");
            flags.add(4096, "EXTEND");
            flags.add(2048, "FLAG4");
            flags.add(1024, "FLAG5");
            flags.add(0, "USER");
            flags.add(256, "ZONE");
            flags.add(512, "HOST");
            flags.add(768, "NTYP3");
            flags.add(128, "FLAG8");
            flags.add(64, "FLAG9");
            flags.add(32, "FLAG10");
            flags.add(16, "FLAG11");
            flags.add(0, "SIG0");
            flags.add(1, "SIG1");
            flags.add(2, "SIG2");
            flags.add(3, "SIG3");
            flags.add(4, "SIG4");
            flags.add(5, "SIG5");
            flags.add(6, "SIG6");
            flags.add(7, "SIG7");
            flags.add(8, "SIG8");
            flags.add(9, "SIG9");
            flags.add(10, "SIG10");
            flags.add(11, "SIG11");
            flags.add(12, "SIG12");
            flags.add(13, "SIG13");
            flags.add(14, "SIG14");
            flags.add(15, "SIG15");
        }

        public static int value(String str) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt < 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException unused) {
                StringTokenizer stringTokenizer = new StringTokenizer(str, "|");
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    int value = flags.getValue(stringTokenizer.nextToken());
                    if (value < 0) {
                        return -1;
                    }
                    i |= value;
                }
                return i;
            }
        }
    }

    KEYRecord() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new KEYRecord();
    }

    public KEYRecord(Name name, int i, long j, int i2, int i3, int i4, byte[] bArr) {
        super(name, 25, i, j, i2, i3, i4, bArr);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public KEYRecord(org.xbill.DNS.Name r12, int r13, long r14, int r16, int r17, int r18, java.security.PublicKey r19) throws org.xbill.DNS.DNSSEC.DNSSECException {
        /*
            r11 = this;
            r8 = r18
            r10 = r19
            byte[] r9 = org.xbill.DNS.DNSSEC.fromPublicKey(r10, r8)
            r2 = 25
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
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.KEYRecord.<init>(org.xbill.DNS.Name, int, long, int, int, int, java.security.PublicKey):void");
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        String identifier = tokenizer.getIdentifier();
        this.flags = Flags.value(identifier);
        if (this.flags >= 0) {
            String identifier2 = tokenizer.getIdentifier();
            this.proto = Protocol.value(identifier2);
            if (this.proto >= 0) {
                String identifier3 = tokenizer.getIdentifier();
                this.alg = DNSSEC.Algorithm.value(identifier3);
                if (this.alg < 0) {
                    throw tokenizer.exception("Invalid algorithm: " + identifier3);
                } else if ((this.flags & 49152) == 49152) {
                    this.key = null;
                } else {
                    this.key = tokenizer.getBase64();
                }
            } else {
                throw tokenizer.exception("Invalid protocol: " + identifier2);
            }
        } else {
            throw tokenizer.exception("Invalid flags: " + identifier);
        }
    }
}

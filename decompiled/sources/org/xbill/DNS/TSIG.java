package org.xbill.DNS;

import com.google.android.gms.nearby.messages.Strategy;
import java.util.Date;
import org.jivesoftware.smackx.EntityCapsManager;
import org.xbill.DNS.utils.HMAC;
import org.xbill.DNS.utils.Base64;

public class TSIG {
    public static final short FUDGE = 300;
    public static final Name HMAC;
    public static final Name HMAC_MD5;
    private static final String HMAC_MD5_STR = "HMAC-MD5.SIG-ALG.REG.INT.";
    private static final String HMAC_SHA1_STR = "hmac-sha1.";
    private static final String HMAC_SHA256_STR = "hmac-sha256.";
    public static final Name HMAC_SHA1 = Name.fromConstantString(HMAC_SHA1_STR);
    public static final Name HMAC_SHA256 = Name.fromConstantString(HMAC_SHA256_STR);
    /* access modifiers changed from: private */
    public Name alg;
    /* access modifiers changed from: private */
    public String digest;
    /* access modifiers changed from: private */
    public byte[] key;
    /* access modifiers changed from: private */
    public Name name;

    static {
        Name fromConstantString = Name.fromConstantString(HMAC_MD5_STR);
        HMAC_MD5 = fromConstantString;
        HMAC = fromConstantString;
    }

    private void getDigest() {
        if (this.alg.equals(HMAC_MD5)) {
            this.digest = "md5";
        } else if (this.alg.equals(HMAC_SHA1)) {
            this.digest = EntityCapsManager.HASH_METHOD;
        } else if (this.alg.equals(HMAC_SHA256)) {
            this.digest = "sha-256";
        } else {
            throw new IllegalArgumentException("Invalid algorithm");
        }
    }

    public TSIG(Name name2, Name name3, byte[] bArr) {
        this.name = name3;
        this.alg = name2;
        this.key = bArr;
        getDigest();
    }

    public TSIG(Name name2, byte[] bArr) {
        this(HMAC_MD5, name2, bArr);
    }

    public TSIG(Name name2, String str, String str2) {
        byte[] fromString = Base64.fromString(str2);
        this.key = fromString;
        if (fromString != null) {
            try {
                this.name = Name.fromString(str, Name.root);
                this.alg = name2;
                getDigest();
            } catch (TextParseException unused) {
                throw new IllegalArgumentException("Invalid TSIG key name");
            }
        } else {
            throw new IllegalArgumentException("Invalid TSIG key string");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TSIG(java.lang.String r2, java.lang.String r3, java.lang.String r4) {
        /*
            r1 = this;
            org.xbill.DNS.Name r0 = HMAC_MD5
            r1.<init>((org.xbill.DNS.Name) r0, (java.lang.String) r3, (java.lang.String) r4)
            java.lang.String r3 = "hmac-md5"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x0010
            r1.alg = r0
            goto L_0x0029
        L_0x0010:
            java.lang.String r3 = "hmac-sha1"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x001d
            org.xbill.DNS.Name r2 = HMAC_SHA1
            r1.alg = r2
            goto L_0x0029
        L_0x001d:
            java.lang.String r3 = "hmac-sha256"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x002d
            org.xbill.DNS.Name r2 = HMAC_SHA256
            r1.alg = r2
        L_0x0029:
            r1.getDigest()
            return
        L_0x002d:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Invalid TSIG algorithm"
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xbill.DNS.TSIG.<init>(java.lang.String, java.lang.String, java.lang.String):void");
    }

    public TSIG(String str, String str2) {
        this(HMAC_MD5, str, str2);
    }

    public static TSIG fromString(String str) {
        String[] split = str.split("[:/]");
        if (split.length < 2 || split.length > 3) {
            throw new IllegalArgumentException("Invalid TSIG key specification");
        } else if (split.length == 3) {
            return new TSIG(split[0], split[1], split[2]);
        } else {
            return new TSIG(HMAC_MD5, split[0], split[1]);
        }
    }

    public TSIGRecord generate(Message message, byte[] bArr, int i, TSIGRecord tSIGRecord) {
        Date date;
        HMAC hmac;
        byte[] bArr2;
        int i2 = i;
        if (i2 != 18) {
            date = new Date();
        } else {
            date = tSIGRecord.getTimeSigned();
        }
        Date date2 = date;
        if (i2 == 0 || i2 == 18) {
            hmac = new HMAC(this.digest, this.key);
        } else {
            hmac = null;
        }
        int intValue = Options.intValue("tsigfudge");
        int i3 = (intValue < 0 || intValue > 32767) ? Strategy.TTL_SECONDS_DEFAULT : intValue;
        if (tSIGRecord != null) {
            DNSOutput dNSOutput = new DNSOutput();
            dNSOutput.writeU16(tSIGRecord.getSignature().length);
            if (hmac != null) {
                hmac.update(dNSOutput.toByteArray());
                hmac.update(tSIGRecord.getSignature());
            }
        }
        if (hmac != null) {
            hmac.update(bArr);
        }
        DNSOutput dNSOutput2 = new DNSOutput();
        this.name.toWireCanonical(dNSOutput2);
        dNSOutput2.writeU16(255);
        dNSOutput2.writeU32(0);
        this.alg.toWireCanonical(dNSOutput2);
        long time = date2.getTime() / 1000;
        dNSOutput2.writeU16((int) (time >> 32));
        dNSOutput2.writeU32(time & 4294967295L);
        dNSOutput2.writeU16(i3);
        dNSOutput2.writeU16(i2);
        dNSOutput2.writeU16(0);
        if (hmac != null) {
            hmac.update(dNSOutput2.toByteArray());
        }
        byte[] sign = hmac != null ? hmac.sign() : new byte[0];
        if (i2 == 18) {
            DNSOutput dNSOutput3 = new DNSOutput();
            long time2 = new Date().getTime() / 1000;
            dNSOutput3.writeU16((int) (time2 >> 32));
            dNSOutput3.writeU32(time2 & 4294967295L);
            bArr2 = dNSOutput3.toByteArray();
        } else {
            bArr2 = null;
        }
        return new TSIGRecord(this.name, 255, 0, this.alg, date2, i3, sign, message.getHeader().getID(), i, bArr2);
    }

    public void apply(Message message, int i, TSIGRecord tSIGRecord) {
        message.addRecord(generate(message, message.toWire(), i, tSIGRecord), 3);
        message.tsigState = 3;
    }

    public void apply(Message message, TSIGRecord tSIGRecord) {
        apply(message, 0, tSIGRecord);
    }

    public void applyStream(Message message, TSIGRecord tSIGRecord, boolean z) {
        if (z) {
            apply(message, tSIGRecord);
            return;
        }
        Date date = new Date();
        HMAC hmac = new HMAC(this.digest, this.key);
        int intValue = Options.intValue("tsigfudge");
        int i = (intValue < 0 || intValue > 32767) ? Strategy.TTL_SECONDS_DEFAULT : intValue;
        DNSOutput dNSOutput = new DNSOutput();
        dNSOutput.writeU16(tSIGRecord.getSignature().length);
        hmac.update(dNSOutput.toByteArray());
        hmac.update(tSIGRecord.getSignature());
        hmac.update(message.toWire());
        DNSOutput dNSOutput2 = new DNSOutput();
        long time = date.getTime() / 1000;
        dNSOutput2.writeU16((int) (time >> 32));
        dNSOutput2.writeU32(time & 4294967295L);
        dNSOutput2.writeU16(i);
        hmac.update(dNSOutput2.toByteArray());
        message.addRecord(new TSIGRecord(this.name, 255, 0, this.alg, date, i, hmac.sign(), message.getHeader().getID(), 0, (byte[]) null), 3);
        message.tsigState = 3;
    }

    public byte verify(Message message, byte[] bArr, int i, TSIGRecord tSIGRecord) {
        message.tsigState = 4;
        TSIGRecord tsig = message.getTSIG();
        HMAC hmac = new HMAC(this.digest, this.key);
        if (tsig == null) {
            return 1;
        }
        if (!tsig.getName().equals(this.name) || !tsig.getAlgorithm().equals(this.alg)) {
            if (Options.check("verbose")) {
                System.err.println("BADKEY failure");
            }
            return 17;
        }
        if (Math.abs(System.currentTimeMillis() - tsig.getTimeSigned().getTime()) <= ((long) tsig.getFudge()) * 1000) {
            if (!(tSIGRecord == null || tsig.getError() == 17 || tsig.getError() == 16)) {
                DNSOutput dNSOutput = new DNSOutput();
                dNSOutput.writeU16(tSIGRecord.getSignature().length);
                hmac.update(dNSOutput.toByteArray());
                hmac.update(tSIGRecord.getSignature());
            }
            message.getHeader().decCount(3);
            byte[] wire = message.getHeader().toWire();
            message.getHeader().incCount(3);
            hmac.update(wire);
            hmac.update(bArr, wire.length, message.tsigstart - wire.length);
            DNSOutput dNSOutput2 = new DNSOutput();
            tsig.getName().toWireCanonical(dNSOutput2);
            dNSOutput2.writeU16(tsig.dclass);
            dNSOutput2.writeU32(tsig.ttl);
            tsig.getAlgorithm().toWireCanonical(dNSOutput2);
            long time = tsig.getTimeSigned().getTime() / 1000;
            dNSOutput2.writeU16((int) (time >> 32));
            dNSOutput2.writeU32(time & 4294967295L);
            dNSOutput2.writeU16(tsig.getFudge());
            dNSOutput2.writeU16(tsig.getError());
            if (tsig.getOther() != null) {
                dNSOutput2.writeU16(tsig.getOther().length);
                dNSOutput2.writeByteArray(tsig.getOther());
            } else {
                dNSOutput2.writeU16(0);
            }
            hmac.update(dNSOutput2.toByteArray());
            if (hmac.verify(tsig.getSignature())) {
                message.tsigState = 1;
                return 0;
            }
            if (Options.check("verbose")) {
                System.err.println("BADSIG failure");
            }
            return 16;
        } else if (!Options.check("verbose")) {
            return 18;
        } else {
            System.err.println("BADTIME failure");
            return 18;
        }
    }

    public int verify(Message message, byte[] bArr, TSIGRecord tSIGRecord) {
        return verify(message, bArr, bArr.length, tSIGRecord);
    }

    public int recordLength() {
        return this.name.length() + 10 + this.alg.length() + 8 + 18 + 4 + 8;
    }

    public static class StreamVerifier {
        private TSIG key;
        private TSIGRecord lastTSIG;
        private int lastsigned;
        private int nresponses = 0;
        private HMAC verifier;

        public StreamVerifier(TSIG tsig, TSIGRecord tSIGRecord) {
            this.key = tsig;
            this.verifier = new HMAC(tsig.digest, this.key.key);
            this.lastTSIG = tSIGRecord;
        }

        public int verify(Message message, byte[] bArr) {
            int i;
            int i2;
            TSIGRecord tsig = message.getTSIG();
            int i3 = this.nresponses + 1;
            this.nresponses = i3;
            if (i3 == 1) {
                int verify = this.key.verify(message, bArr, this.lastTSIG);
                if (verify == 0) {
                    byte[] signature = tsig.getSignature();
                    DNSOutput dNSOutput = new DNSOutput();
                    dNSOutput.writeU16(signature.length);
                    this.verifier.update(dNSOutput.toByteArray());
                    this.verifier.update(signature);
                }
                this.lastTSIG = tsig;
                return verify;
            }
            if (tsig != null) {
                message.getHeader().decCount(3);
            }
            byte[] wire = message.getHeader().toWire();
            if (tsig != null) {
                message.getHeader().incCount(3);
            }
            this.verifier.update(wire);
            if (tsig == null) {
                i2 = bArr.length;
                i = wire.length;
            } else {
                i2 = message.tsigstart;
                i = wire.length;
            }
            this.verifier.update(bArr, wire.length, i2 - i);
            if (tsig != null) {
                this.lastsigned = this.nresponses;
                this.lastTSIG = tsig;
                if (!tsig.getName().equals(this.key.name) || !tsig.getAlgorithm().equals(this.key.alg)) {
                    if (Options.check("verbose")) {
                        System.err.println("BADKEY failure");
                    }
                    message.tsigState = 4;
                    return 17;
                }
                DNSOutput dNSOutput2 = new DNSOutput();
                long time = tsig.getTimeSigned().getTime() / 1000;
                dNSOutput2.writeU16((int) (time >> 32));
                dNSOutput2.writeU32(time & 4294967295L);
                dNSOutput2.writeU16(tsig.getFudge());
                this.verifier.update(dNSOutput2.toByteArray());
                if (!this.verifier.verify(tsig.getSignature())) {
                    if (Options.check("verbose")) {
                        System.err.println("BADSIG failure");
                    }
                    message.tsigState = 4;
                    return 16;
                }
                this.verifier.clear();
                DNSOutput dNSOutput3 = new DNSOutput();
                dNSOutput3.writeU16(tsig.getSignature().length);
                this.verifier.update(dNSOutput3.toByteArray());
                this.verifier.update(tsig.getSignature());
                message.tsigState = 1;
                return 0;
            }
            if (this.nresponses - this.lastsigned >= 100) {
                message.tsigState = 4;
                return 1;
            }
            message.tsigState = 2;
            return 0;
        }
    }
}

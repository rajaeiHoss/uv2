package org.xbill.DNS;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.DSAPublicKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import org.jivesoftware.smackx.EntityCapsManager;

public class DNSSEC {
    private static final int ASN1_INT = 2;
    private static final int ASN1_SEQ = 48;
    private static final int DSA_LEN = 20;

    public static class Algorithm {
        public static final int DH = 2;
        public static final int DSA = 3;
        public static final int DSA_NSEC3_SHA1 = 6;
        public static final int ECC = 4;
        public static final int INDIRECT = 252;
        public static final int PRIVATEDNS = 253;
        public static final int PRIVATEOID = 254;
        public static final int RSAMD5 = 1;
        public static final int RSASHA1 = 5;
        public static final int RSASHA256 = 8;
        public static final int RSASHA512 = 10;
        public static final int RSA_NSEC3_SHA1 = 7;
        private static Mnemonic algs;

        private Algorithm() {
        }

        static {
            Mnemonic mnemonic = new Mnemonic("DNSSEC algorithm", 2);
            algs = mnemonic;
            mnemonic.setMaximum(255);
            algs.setNumericAllowed(true);
            algs.add(1, "RSAMD5");
            algs.add(2, "DH");
            algs.add(3, "DSA");
            algs.add(4, "ECC");
            algs.add(5, "RSASHA1");
            algs.add(6, "DSA-NSEC3-SHA1");
            algs.add(7, "RSA-NSEC3-SHA1");
            algs.add(8, "RSASHA256");
            algs.add(10, "RSASHA512");
            algs.add(252, "INDIRECT");
            algs.add(253, "PRIVATEDNS");
            algs.add(254, "PRIVATEOID");
        }

        public static String string(int i) {
            return algs.getText(i);
        }

        public static int value(String str) {
            return algs.getValue(str);
        }
    }

    private DNSSEC() {
    }

    private static void digestSIG(DNSOutput dNSOutput, SIGBase sIGBase) {
        dNSOutput.writeU16(sIGBase.getTypeCovered());
        dNSOutput.writeU8(sIGBase.getAlgorithm());
        dNSOutput.writeU8(sIGBase.getLabels());
        dNSOutput.writeU32(sIGBase.getOrigTTL());
        dNSOutput.writeU32(sIGBase.getExpire().getTime() / 1000);
        dNSOutput.writeU32(sIGBase.getTimeSigned().getTime() / 1000);
        dNSOutput.writeU16(sIGBase.getFootprint());
        sIGBase.getSigner().toWireCanonical(dNSOutput);
    }

    public static byte[] digestRRset(RRSIGRecord rRSIGRecord, RRset rRset) {
        DNSOutput dNSOutput = new DNSOutput();
        digestSIG(dNSOutput, rRSIGRecord);
        int size = rRset.size();
        Record[] recordArr = new Record[size];
        Iterator rrs = rRset.rrs();
        Name name = rRset.getName();
        int labels = rRSIGRecord.getLabels() + 1;
        Name wild = name.labels() > labels ? name.wild(name.labels() - labels) : null;
        int i = size;
        while (rrs.hasNext()) {
            i--;
            recordArr[i] = (Record) rrs.next();
        }
        Arrays.sort(recordArr);
        DNSOutput dNSOutput2 = new DNSOutput();
        if (wild != null) {
            wild.toWireCanonical(dNSOutput2);
        } else {
            name.toWireCanonical(dNSOutput2);
        }
        dNSOutput2.writeU16(rRset.getType());
        dNSOutput2.writeU16(rRset.getDClass());
        dNSOutput2.writeU32(rRSIGRecord.getOrigTTL());
        for (int i2 = 0; i2 < size; i2++) {
            dNSOutput.writeByteArray(dNSOutput2.toByteArray());
            int current = dNSOutput.current();
            dNSOutput.writeU16(0);
            dNSOutput.writeByteArray(recordArr[i2].rdataToWireCanonical());
            dNSOutput.save();
            dNSOutput.jump(current);
            dNSOutput.writeU16((dNSOutput.current() - current) - 2);
            dNSOutput.restore();
        }
        return dNSOutput.toByteArray();
    }

    public static byte[] digestMessage(SIGRecord sIGRecord, Message message, byte[] bArr) {
        DNSOutput dNSOutput = new DNSOutput();
        digestSIG(dNSOutput, sIGRecord);
        if (bArr != null) {
            dNSOutput.writeByteArray(bArr);
        }
        message.toWire(dNSOutput);
        return dNSOutput.toByteArray();
    }

    public static class DNSSECException extends Exception {
        DNSSECException(String str) {
            super(str);
        }
    }

    public static class UnsupportedAlgorithmException extends DNSSECException {
        UnsupportedAlgorithmException(int i) {
            super("Unsupported algorithm: " + i);
        }
    }

    public static class MalformedKeyException extends DNSSECException {
        MalformedKeyException(KEYBase kEYBase) {
            super("Invalid key data: " + kEYBase.rdataToString());
        }
    }

    public static class KeyMismatchException extends DNSSECException {
        private KEYBase key;
        private SIGBase sig;

        KeyMismatchException(KEYBase kEYBase, SIGBase sIGBase) {
            super("key " + kEYBase.getName() + "/" + Algorithm.string(kEYBase.getAlgorithm()) + "/" + kEYBase.getFootprint() + " " + "does not match signature " + sIGBase.getSigner() + "/" + Algorithm.string(sIGBase.getAlgorithm()) + "/" + sIGBase.getFootprint());
        }
    }

    public static class SignatureExpiredException extends DNSSECException {
        private Date now;
        private Date when;

        SignatureExpiredException(Date date, Date date2) {
            super("signature expired");
            this.when = date;
            this.now = date2;
        }

        public Date getExpiration() {
            return this.when;
        }

        public Date getVerifyTime() {
            return this.now;
        }
    }

    public static class SignatureNotYetValidException extends DNSSECException {
        private Date now;
        private Date when;

        SignatureNotYetValidException(Date date, Date date2) {
            super("signature is not yet valid");
            this.when = date;
            this.now = date2;
        }

        public Date getExpiration() {
            return this.when;
        }

        public Date getVerifyTime() {
            return this.now;
        }
    }

    public static class SignatureVerificationException extends DNSSECException {
        SignatureVerificationException() {
            super("signature verification failed");
        }
    }

    public static class IncompatibleKeyException extends IllegalArgumentException {
        IncompatibleKeyException() {
            super("incompatible keys");
        }
    }

    private static int BigIntegerLength(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) / 8;
    }

    private static BigInteger readBigInteger(DNSInput dNSInput, int i) throws IOException {
        return new BigInteger(1, dNSInput.readByteArray(i));
    }

    private static BigInteger readBigInteger(DNSInput dNSInput) {
        return new BigInteger(1, dNSInput.readByteArray());
    }

    private static void writeBigInteger(DNSOutput dNSOutput, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            dNSOutput.writeByteArray(byteArray, 1, byteArray.length - 1);
        } else {
            dNSOutput.writeByteArray(byteArray);
        }
    }

    private static PublicKey toRSAPublicKey(KEYBase kEYBase) throws IOException, GeneralSecurityException {
        DNSInput dNSInput = new DNSInput(kEYBase.getKey());
        int readU8 = dNSInput.readU8();
        if (readU8 == 0) {
            readU8 = dNSInput.readU16();
        }
        BigInteger readBigInteger = readBigInteger(dNSInput, readU8);
        return KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(readBigInteger(dNSInput), readBigInteger));
    }

    private static PublicKey toDSAPublicKey(KEYBase kEYBase) throws IOException, GeneralSecurityException, MalformedKeyException {
        DNSInput dNSInput = new DNSInput(kEYBase.getKey());
        int readU8 = dNSInput.readU8();
        if (readU8 <= 8) {
            BigInteger readBigInteger = readBigInteger(dNSInput, 20);
            int i = (readU8 * 8) + 64;
            BigInteger readBigInteger2 = readBigInteger(dNSInput, i);
            BigInteger readBigInteger3 = readBigInteger(dNSInput, i);
            return KeyFactory.getInstance("DSA").generatePublic(new DSAPublicKeySpec(readBigInteger(dNSInput, i), readBigInteger2, readBigInteger, readBigInteger3));
        }
        throw new MalformedKeyException(kEYBase);
    }

    static PublicKey toPublicKey(KEYBase kEYBase) throws DNSSECException {
        try {
            int algorithm = kEYBase.getAlgorithm();
            if (algorithm != 1) {
                if (algorithm != 3) {
                    if (!(algorithm == 10 || algorithm == 5)) {
                        if (algorithm != 6) {
                            if (!(algorithm == 7 || algorithm == 8)) {
                                throw new UnsupportedAlgorithmException(algorithm);
                            }
                        }
                    }
                }
                return toDSAPublicKey(kEYBase);
            }
            return toRSAPublicKey(kEYBase);
        } catch (IOException unused) {
            throw new MalformedKeyException(kEYBase);
        } catch (GeneralSecurityException e) {
            throw new DNSSECException(e.toString());
        }
    }

    private static byte[] fromRSAPublicKey(RSAPublicKey rSAPublicKey) {
        DNSOutput dNSOutput = new DNSOutput();
        BigInteger publicExponent = rSAPublicKey.getPublicExponent();
        BigInteger modulus = rSAPublicKey.getModulus();
        int BigIntegerLength = BigIntegerLength(publicExponent);
        if (BigIntegerLength < 256) {
            dNSOutput.writeU8(BigIntegerLength);
        } else {
            dNSOutput.writeU8(0);
            dNSOutput.writeU16(BigIntegerLength);
        }
        writeBigInteger(dNSOutput, publicExponent);
        writeBigInteger(dNSOutput, modulus);
        return dNSOutput.toByteArray();
    }

    private static byte[] fromDSAPublicKey(DSAPublicKey dSAPublicKey) {
        DNSOutput dNSOutput = new DNSOutput();
        BigInteger q = dSAPublicKey.getParams().getQ();
        BigInteger p = dSAPublicKey.getParams().getP();
        BigInteger g = dSAPublicKey.getParams().getG();
        BigInteger y = dSAPublicKey.getY();
        dNSOutput.writeU8((p.toByteArray().length - 64) / 8);
        writeBigInteger(dNSOutput, q);
        writeBigInteger(dNSOutput, p);
        writeBigInteger(dNSOutput, g);
        writeBigInteger(dNSOutput, y);
        return dNSOutput.toByteArray();
    }

    static byte[] fromPublicKey(PublicKey publicKey, int i) throws DNSSECException {
        if (i != 1) {
            if (i != 3) {
                if (!(i == 10 || i == 5)) {
                    if (i != 6) {
                        if (!(i == 7 || i == 8)) {
                            throw new UnsupportedAlgorithmException(i);
                        }
                    }
                }
            }
            if (publicKey instanceof DSAPublicKey) {
                return fromDSAPublicKey((DSAPublicKey) publicKey);
            }
            throw new IncompatibleKeyException();
        }
        if (publicKey instanceof RSAPublicKey) {
            return fromRSAPublicKey((RSAPublicKey) publicKey);
        }
        throw new IncompatibleKeyException();
    }

    private static String algString(int i) throws UnsupportedAlgorithmException {
        if (i == 1) {
            return "MD5withRSA";
        }
        if (i == 3) {
            return "SHA1withDSA";
        }
        if (i == 10) {
            return "SHA512withRSA";
        }
        if (i == 5) {
            return "SHA1withRSA";
        }
        if (i == 6) {
            return "SHA1withDSA";
        }
        if (i == 7) {
            return "SHA1withRSA";
        }
        if (i == 8) {
            return "SHA256withRSA";
        }
        throw new UnsupportedAlgorithmException(i);
    }

    private static byte[] DSASignaturefromDNS(byte[] bArr) throws DNSSECException, IOException {
        if (bArr.length == 41) {
            DNSInput dNSInput = new DNSInput(bArr);
            DNSOutput dNSOutput = new DNSOutput();
            dNSInput.readU8();
            byte[] readByteArray = dNSInput.readByteArray(20);
            int i = 21;
            int i2 = readByteArray[0] < 0 ? 21 : 20;
            byte[] readByteArray2 = dNSInput.readByteArray(20);
            if (readByteArray2[0] >= 0) {
                i = 20;
            }
            dNSOutput.writeU8(48);
            dNSOutput.writeU8(i2 + i + 4);
            dNSOutput.writeU8(2);
            dNSOutput.writeU8(i2);
            if (i2 > 20) {
                dNSOutput.writeU8(0);
            }
            dNSOutput.writeByteArray(readByteArray);
            dNSOutput.writeU8(2);
            dNSOutput.writeU8(i);
            if (i > 20) {
                dNSOutput.writeU8(0);
            }
            dNSOutput.writeByteArray(readByteArray2);
            return dNSOutput.toByteArray();
        }
        throw new SignatureVerificationException();
    }

    private static byte[] DSASignaturetoDNS(byte[] bArr, int i) throws IOException {
        DNSInput dNSInput = new DNSInput(bArr);
        DNSOutput dNSOutput = new DNSOutput();
        dNSOutput.writeU8(i);
        if (dNSInput.readU8() == 48) {
            dNSInput.readU8();
            if (dNSInput.readU8() == 2) {
                int readU8 = dNSInput.readU8();
                if (readU8 == 21) {
                    if (dNSInput.readU8() != 0) {
                        throw new IOException();
                    }
                } else if (readU8 != 20) {
                    throw new IOException();
                }
                dNSOutput.writeByteArray(dNSInput.readByteArray(20));
                if (dNSInput.readU8() == 2) {
                    int readU82 = dNSInput.readU8();
                    if (readU82 == 21) {
                        if (dNSInput.readU8() != 0) {
                            throw new IOException();
                        }
                    } else if (readU82 != 20) {
                        throw new IOException();
                    }
                    dNSOutput.writeByteArray(dNSInput.readByteArray(20));
                    return dNSOutput.toByteArray();
                }
                throw new IOException();
            }
            throw new IOException();
        }
        throw new IOException();
    }

    private static void verify(PublicKey publicKey, int i, byte[] bArr, byte[] bArr2) throws DNSSECException {
        if (publicKey instanceof DSAPublicKey) {
            try {
                bArr2 = DSASignaturefromDNS(bArr2);
            } catch (IOException unused) {
                throw new IllegalStateException();
            }
        }
        try {
            Signature instance = Signature.getInstance(algString(i));
            instance.initVerify(publicKey);
            instance.update(bArr);
            if (!instance.verify(bArr2)) {
                throw new SignatureVerificationException();
            }
        } catch (GeneralSecurityException e) {
            throw new DNSSECException(e.toString());
        }
    }

    private static boolean matches(SIGBase sIGBase, KEYBase kEYBase) {
        return kEYBase.getAlgorithm() == sIGBase.getAlgorithm() && kEYBase.getFootprint() == sIGBase.getFootprint() && kEYBase.getName().equals(sIGBase.getSigner());
    }

    public static void verify(RRset rRset, RRSIGRecord rRSIGRecord, DNSKEYRecord dNSKEYRecord) throws DNSSECException {
        if (matches(rRSIGRecord, dNSKEYRecord)) {
            Date date = new Date();
            if (date.compareTo(rRSIGRecord.getExpire()) > 0) {
                throw new SignatureExpiredException(rRSIGRecord.getExpire(), date);
            } else if (date.compareTo(rRSIGRecord.getTimeSigned()) >= 0) {
                verify(dNSKEYRecord.getPublicKey(), rRSIGRecord.getAlgorithm(), digestRRset(rRSIGRecord, rRset), rRSIGRecord.getSignature());
            } else {
                throw new SignatureNotYetValidException(rRSIGRecord.getTimeSigned(), date);
            }
        } else {
            throw new KeyMismatchException(dNSKEYRecord, rRSIGRecord);
        }
    }

    private static byte[] sign(PrivateKey privateKey, PublicKey publicKey, int i, byte[] bArr) throws DNSSECException {
        try {
            Signature instance = Signature.getInstance(algString(i));
            instance.initSign(privateKey);
            instance.update(bArr);
            byte[] sign = instance.sign();
            if (!(publicKey instanceof DSAPublicKey)) {
                return sign;
            }
            try {
                return DSASignaturetoDNS(sign, (BigIntegerLength(((DSAPublicKey) publicKey).getParams().getP()) - 64) / 8);
            } catch (IOException unused) {
                throw new IllegalStateException();
            }
        } catch (GeneralSecurityException e) {
            throw new DNSSECException(e.toString());
        }
    }

    static void checkAlgorithm(PrivateKey privateKey, int i) throws UnsupportedAlgorithmException {
        if (i != 1) {
            if (i != 3) {
                if (!(i == 10 || i == 5)) {
                    if (i != 6) {
                        if (!(i == 7 || i == 8)) {
                            throw new UnsupportedAlgorithmException(i);
                        }
                    }
                }
            }
            if (!(privateKey instanceof DSAPrivateKey)) {
                throw new IncompatibleKeyException();
            }
            return;
        }
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new IncompatibleKeyException();
        }
    }

    public static RRSIGRecord sign(RRset rRset, DNSKEYRecord dNSKEYRecord, PrivateKey privateKey, Date date, Date date2) throws DNSSECException {
        int algorithm = dNSKEYRecord.getAlgorithm();
        checkAlgorithm(privateKey, algorithm);
        RRSIGRecord record = new RRSIGRecord(rRset.getName(), rRset.getDClass(), rRset.getTTL(), rRset.getType(), algorithm, rRset.getTTL(), date2, date, dNSKEYRecord.getFootprint(), dNSKEYRecord.getName(), (byte[]) null);
        record.setSignature(sign(privateKey, dNSKEYRecord.getPublicKey(), algorithm, digestRRset(record, rRset)));
        return record;
    }

    static SIGRecord signMessage(Message message, SIGRecord sIGRecord, KEYRecord kEYRecord, PrivateKey privateKey, Date date, Date date2) throws DNSSECException {
        int algorithm = kEYRecord.getAlgorithm();
        checkAlgorithm(privateKey, algorithm);
        SIGRecord sIGRecord2 = new SIGRecord(Name.root, 255, 0, 0, algorithm, 0, date2, date, kEYRecord.getFootprint(), kEYRecord.getName(), (byte[]) null);
        DNSOutput dNSOutput = new DNSOutput();
        digestSIG(dNSOutput, sIGRecord2);
        if (sIGRecord != null) {
            dNSOutput.writeByteArray(sIGRecord.getSignature());
        }
        message.toWire(dNSOutput);
        sIGRecord2.setSignature(sign(privateKey, kEYRecord.getPublicKey(), algorithm, dNSOutput.toByteArray()));
        return sIGRecord2;
    }

    static void verifyMessage(Message message, byte[] bArr, SIGRecord sIGRecord, SIGRecord sIGRecord2, KEYRecord kEYRecord) throws DNSSECException {
        if (matches(sIGRecord, kEYRecord)) {
            Date date = new Date();
            if (date.compareTo(sIGRecord.getExpire()) > 0) {
                throw new SignatureExpiredException(sIGRecord.getExpire(), date);
            } else if (date.compareTo(sIGRecord.getTimeSigned()) >= 0) {
                DNSOutput dNSOutput = new DNSOutput();
                digestSIG(dNSOutput, sIGRecord);
                if (sIGRecord2 != null) {
                    dNSOutput.writeByteArray(sIGRecord2.getSignature());
                }
                Header header = (Header) message.getHeader().clone();
                header.decCount(3);
                dNSOutput.writeByteArray(header.toWire());
                dNSOutput.writeByteArray(bArr, 12, message.sig0start - 12);
                verify(kEYRecord.getPublicKey(), sIGRecord.getAlgorithm(), dNSOutput.toByteArray(), sIGRecord.getSignature());
            } else {
                throw new SignatureNotYetValidException(sIGRecord.getTimeSigned(), date);
            }
        } else {
            throw new KeyMismatchException(kEYRecord, sIGRecord);
        }
    }

    static byte[] generateDS(DNSKEYRecord dNSKEYRecord, int i) {
        MessageDigest messageDigest;
        DNSOutput dNSOutput = new DNSOutput();
        dNSOutput.writeU16(dNSKEYRecord.getFootprint());
        dNSOutput.writeU8(dNSKEYRecord.getAlgorithm());
        dNSOutput.writeU8(i);
        if (i == 1) {
            try {
                messageDigest = MessageDigest.getInstance(EntityCapsManager.HASH_METHOD);
            } catch (NoSuchAlgorithmException unused) {
                throw new IllegalStateException("no message digest support");
            }
        } else if (i == 2) {
            try {
                messageDigest = MessageDigest.getInstance("sha-256");
            } catch (NoSuchAlgorithmException unused) {
                throw new IllegalStateException("no message digest support");
            }
        } else {
            throw new IllegalArgumentException("unknown DS digest type " + i);
        }
        messageDigest.update(dNSKEYRecord.getName().toWire());
        messageDigest.update(dNSKEYRecord.rdataToWireCanonical());
        dNSOutput.writeByteArray(messageDigest.digest());
        return dNSOutput.toByteArray();
    }
}

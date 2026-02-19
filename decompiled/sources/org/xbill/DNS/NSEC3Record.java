package org.xbill.DNS;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.jivesoftware.smackx.EntityCapsManager;
import org.xbill.DNS.utils.Base16;
import org.xbill.DNS.utils.Base32;

public class NSEC3Record extends Record {
    public static final int SHA1_DIGEST_ID = 1;
    private static final Base32 b32 = new Base32(Base32.Alphabet.BASE32HEX, false, false);
    private static final long serialVersionUID = -7123504635968932855L;
    private int flags;
    private int hashAlg;
    private int iterations;
    private byte[] next;
    private byte[] salt;
    private TypeBitmap types;

    public static class Flags {
        public static final int OPT_OUT = 1;

        private Flags() {
        }
    }

    public static class Digest {
        public static final int SHA1 = 1;

        private Digest() {
        }
    }

    NSEC3Record() {
    }

    /* access modifiers changed from: package-private */
    public Record getObject() {
        return new NSEC3Record();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NSEC3Record(Name name, int i, long j, int i2, int i3, int i4, byte[] bArr, byte[] bArr2, int[] iArr) {
        super(name, 50, i, j);
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        int i5 = i2;
        this.hashAlg = checkU8("hashAlg", i2);
        int i6 = i3;
        this.flags = checkU8("flags", i3);
        this.iterations = checkU16("iterations", i4);
        if (bArr3 != null) {
            if (bArr3.length > 255) {
                throw new IllegalArgumentException("Invalid salt");
            } else if (bArr3.length > 0) {
                byte[] bArr5 = new byte[bArr3.length];
                this.salt = bArr5;
                System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
            }
        }
        if (bArr4.length <= 255) {
            byte[] bArr6 = new byte[bArr4.length];
            this.next = bArr6;
            System.arraycopy(bArr4, 0, bArr6, 0, bArr4.length);
            this.types = new TypeBitmap(iArr);
            return;
        }
        throw new IllegalArgumentException("Invalid next hash");
    }

    /* access modifiers changed from: package-private */
    public void rrFromWire(DNSInput dNSInput) throws IOException {
        this.hashAlg = dNSInput.readU8();
        this.flags = dNSInput.readU8();
        this.iterations = dNSInput.readU16();
        int readU8 = dNSInput.readU8();
        if (readU8 > 0) {
            this.salt = dNSInput.readByteArray(readU8);
        } else {
            this.salt = null;
        }
        this.next = dNSInput.readByteArray(dNSInput.readU8());
        this.types = new TypeBitmap(dNSInput);
    }

    /* access modifiers changed from: package-private */
    public void rrToWire(DNSOutput dNSOutput, Compression compression, boolean z) {
        dNSOutput.writeU8(this.hashAlg);
        dNSOutput.writeU8(this.flags);
        dNSOutput.writeU16(this.iterations);
        byte[] bArr = this.salt;
        if (bArr != null) {
            dNSOutput.writeU8(bArr.length);
            dNSOutput.writeByteArray(this.salt);
        } else {
            dNSOutput.writeU8(0);
        }
        dNSOutput.writeU8(this.next.length);
        dNSOutput.writeByteArray(this.next);
        this.types.toWire(dNSOutput);
    }

    /* access modifiers changed from: package-private */
    public void rdataFromString(Tokenizer tokenizer, Name name) throws IOException {
        this.hashAlg = tokenizer.getUInt8();
        this.flags = tokenizer.getUInt8();
        this.iterations = tokenizer.getUInt16();
        if (tokenizer.getString().equals("-")) {
            this.salt = null;
        } else {
            tokenizer.unget();
            byte[] hexString = tokenizer.getHexString();
            this.salt = hexString;
            if (hexString.length > 255) {
                throw tokenizer.exception("salt value too long");
            }
        }
        this.next = tokenizer.getBase32String(b32);
        this.types = new TypeBitmap(tokenizer);
    }

    /* access modifiers changed from: package-private */
    public String rrToString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.hashAlg);
        stringBuffer.append(' ');
        stringBuffer.append(this.flags);
        stringBuffer.append(' ');
        stringBuffer.append(this.iterations);
        stringBuffer.append(' ');
        byte[] bArr = this.salt;
        if (bArr == null) {
            stringBuffer.append('-');
        } else {
            stringBuffer.append(Base16.toString(bArr));
        }
        stringBuffer.append(' ');
        stringBuffer.append(b32.toString(this.next));
        if (!this.types.empty()) {
            stringBuffer.append(' ');
            stringBuffer.append(this.types.toString());
        }
        return stringBuffer.toString();
    }

    public int getHashAlgorithm() {
        return this.hashAlg;
    }

    public int getFlags() {
        return this.flags;
    }

    public int getIterations() {
        return this.iterations;
    }

    public byte[] getSalt() {
        return this.salt;
    }

    public byte[] getNext() {
        return this.next;
    }

    public int[] getTypes() {
        return this.types.toArray();
    }

    public boolean hasType(int i) {
        return this.types.contains(i);
    }

    static byte[] hashName(Name name, int i, int i2, byte[] bArr) throws NoSuchAlgorithmException {
        if (i == 1) {
            MessageDigest instance = MessageDigest.getInstance(EntityCapsManager.HASH_METHOD);
            byte[] bArr2 = null;
            for (int i3 = 0; i3 <= i2; i3++) {
                instance.reset();
                if (i3 == 0) {
                    instance.update(name.toWireCanonical());
                } else {
                    instance.update(bArr2);
                }
                if (bArr != null) {
                    instance.update(bArr);
                }
                bArr2 = instance.digest();
            }
            return bArr2;
        }
        throw new NoSuchAlgorithmException("Unknown NSEC3 algorithmidentifier: " + i);
    }

    public byte[] hashName(Name name) throws NoSuchAlgorithmException {
        return hashName(name, this.hashAlg, this.iterations, this.salt);
    }
}

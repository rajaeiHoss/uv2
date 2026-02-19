package org.xbill.DNS.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class HMAC {
    private static final byte IPAD = 54;
    private static final byte OPAD = 92;
    private static final byte PADLEN = 64;
    MessageDigest digest;
    private byte[] ipad;
    private byte[] opad;

    private void init(byte[] bArr) {
        if (bArr.length > 64) {
            bArr = this.digest.digest(bArr);
            this.digest.reset();
        }
        this.ipad = new byte[64];
        this.opad = new byte[64];
        int i = 0;
        while (i < bArr.length) {
            this.ipad[i] = (byte) (54 ^ bArr[i]);
            this.opad[i] = (byte) (92 ^ bArr[i]);
            i++;
        }
        while (i < 64) {
            this.ipad[i] = IPAD;
            this.opad[i] = OPAD;
            i++;
        }
        this.digest.update(this.ipad);
    }

    public HMAC(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.reset();
        this.digest = messageDigest;
        init(bArr);
    }

    public HMAC(String str, byte[] bArr) {
        try {
            this.digest = MessageDigest.getInstance(str);
            init(bArr);
        } catch (NoSuchAlgorithmException unused) {
            throw new IllegalArgumentException("unknown digest algorithm " + str);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    public void update(byte[] bArr) {
        this.digest.update(bArr);
    }

    public byte[] sign() {
        byte[] digest2 = this.digest.digest();
        this.digest.reset();
        this.digest.update(this.opad);
        return this.digest.digest(digest2);
    }

    public boolean verify(byte[] bArr) {
        return Arrays.equals(bArr, sign());
    }

    public void clear() {
        this.digest.reset();
        this.digest.update(this.ipad);
    }
}

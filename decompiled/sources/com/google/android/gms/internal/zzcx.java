package com.google.android.gms.internal;

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class zzcx {
    private static Cipher zzaic;
    private static final Object zzaid = new Object();
    private static final Object zzaie = new Object();
    private final SecureRandom zzaib = null;

    public zzcx(SecureRandom secureRandom) {
    }

    private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher;
        synchronized (zzaie) {
            if (zzaic == null) {
                zzaic = Cipher.getInstance("AES/CBC/PKCS5Padding");
            }
            cipher = zzaic;
        }
        return cipher;
    }

    public final byte[] zzb(byte[] bArr, String str) throws zzcy {
        byte[] doFinal;
        if (bArr.length == 16) {
            try {
                byte[] zza = zzbv.zza(str, false);
                if (zza.length > 16) {
                    ByteBuffer allocate = ByteBuffer.allocate(zza.length);
                    allocate.put(zza);
                    allocate.flip();
                    byte[] bArr2 = new byte[16];
                    byte[] bArr3 = new byte[(zza.length - 16)];
                    allocate.get(bArr2);
                    allocate.get(bArr3);
                    SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                    synchronized (zzaid) {
                        getCipher().init(2, secretKeySpec, new IvParameterSpec(bArr2));
                        doFinal = getCipher().doFinal(bArr3);
                    }
                    return doFinal;
                }
                throw new zzcy(this);
            } catch (NoSuchAlgorithmException e) {
                throw new zzcy(this, e);
            } catch (InvalidKeyException e2) {
                throw new zzcy(this, e2);
            } catch (IllegalBlockSizeException e3) {
                throw new zzcy(this, e3);
            } catch (NoSuchPaddingException e4) {
                throw new zzcy(this, e4);
            } catch (BadPaddingException e5) {
                throw new zzcy(this, e5);
            } catch (InvalidAlgorithmParameterException e6) {
                throw new zzcy(this, e6);
            } catch (IllegalArgumentException e7) {
                throw new zzcy(this, e7);
            }
        } else {
            throw new zzcy(this);
        }
    }

    public final String zzc(byte[] bArr, byte[] bArr2) throws zzcy {
        byte[] doFinal;
        byte[] iv;
        if (bArr.length == 16) {
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
                synchronized (zzaid) {
                    getCipher().init(1, secretKeySpec, (SecureRandom) null);
                    doFinal = getCipher().doFinal(bArr2);
                    iv = getCipher().getIV();
                }
                int length = doFinal.length + iv.length;
                ByteBuffer allocate = ByteBuffer.allocate(length);
                allocate.put(iv).put(doFinal);
                allocate.flip();
                byte[] bArr3 = new byte[length];
                allocate.get(bArr3);
                return zzbv.zza(bArr3, false);
            } catch (NoSuchAlgorithmException e) {
                throw new zzcy(this, e);
            } catch (InvalidKeyException e2) {
                throw new zzcy(this, e2);
            } catch (IllegalBlockSizeException e3) {
                throw new zzcy(this, e3);
            } catch (NoSuchPaddingException e4) {
                throw new zzcy(this, e4);
            } catch (BadPaddingException e5) {
                throw new zzcy(this, e5);
            }
        } else {
            throw new zzcy(this);
        }
    }

    public final byte[] zzl(String str) throws zzcy {
        try {
            byte[] zza = zzbv.zza(str, false);
            if (zza.length == 32) {
                byte[] bArr = new byte[16];
                ByteBuffer.wrap(zza, 4, 16).get(bArr);
                for (int i = 0; i < 16; i++) {
                    bArr[i] = (byte) (bArr[i] ^ 68);
                }
                return bArr;
            }
            throw new zzcy(this);
        } catch (IllegalArgumentException e) {
            throw new zzcy(this, e);
        }
    }
}

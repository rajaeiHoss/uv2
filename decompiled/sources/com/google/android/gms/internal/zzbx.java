package com.google.android.gms.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzbx {
    private static boolean zzyw = false;
    /* access modifiers changed from: private */
    public static MessageDigest zzyx;
    private static final Object zzyy = new Object();
    private static final Object zzyz = new Object();
    static CountDownLatch zzza = new CountDownLatch(1);

    static String zza(zzba zzba, String str) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] bArr;
        byte[] zzc = zzfls.zzc(zzba);
        if (((Boolean) zzlc.zzio().zzd(zzoi.zzbrr)).booleanValue()) {
            bArr = zza(zzc, str);
        } else if (zzdq.zzajk != null) {
            byte[] zzd = zzdq.zzajk.zzd(zzc, str != null ? str.getBytes() : new byte[0]);
            zzbg zzbg = new zzbg();
            zzbg.zzgp = new byte[][]{zzd};
            zzbg.zzgr = 2;
            bArr = zzfls.zzc(zzbg);
        } else {
            throw new GeneralSecurityException();
        }
        return zzbv.zza(bArr, true);
    }

    private static Vector<byte[]> zza(byte[] bArr, int i) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        int length = ((bArr.length + 255) - 1) / 255;
        Vector<byte[]> vector = new Vector<>();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 * 255;
            try {
                vector.add(Arrays.copyOfRange(bArr, i3, bArr.length - i3 > 255 ? i3 + 255 : bArr.length));
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                return null;
            }
        }
        return vector;
    }

    private static byte[] zza(byte[] bArr, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Vector<byte[]> zza = zza(bArr, 255);
        if (zza == null || zza.size() == 0) {
            return zza(zzfls.zzc(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM)), str, true);
        }
        zzbg zzbg = new zzbg();
        zzbg.zzgp = new byte[zza.size()][];
        Iterator<byte[]> it = zza.iterator();
        int i = 0;
        while (it.hasNext()) {
            zzbg.zzgp[i] = zza(it.next(), str, false);
            i++;
        }
        zzbg.zzgk = zzb(bArr);
        return zzfls.zzc(zzbg);
    }

    private static byte[] zza(byte[] bArr, String str, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        ByteBuffer byteBuffer;
        int i = z ? 239 : 255;
        if (bArr.length > i) {
            bArr = zzfls.zzc(zzc(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        }
        if (bArr.length < i) {
            byte[] bArr2 = new byte[(i - bArr.length)];
            new SecureRandom().nextBytes(bArr2);
            byteBuffer = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr).put(bArr2);
        } else {
            byteBuffer = ByteBuffer.allocate(i + 1).put((byte) bArr.length).put(bArr);
        }
        byte[] array = byteBuffer.array();
        if (z) {
            array = ByteBuffer.allocate(256).put(zzb(array)).put(array).array();
        }
        byte[] bArr3 = new byte[256];
        new zzca().zza(array, bArr3);
        if (str != null && str.length() > 0) {
            if (str.length() > 32) {
                str = str.substring(0, 32);
            }
            new zzffa(str.getBytes("UTF-8")).zzay(bArr3);
        }
        return bArr3;
    }

    private static MessageDigest zzaa() {
        boolean z;
        MessageDigest messageDigest;
        zzz();
        try {
            z = zzza.await(2, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
            z = false;
        }
        if (z && (messageDigest = zzyx) != null) {
            return messageDigest;
        }
        return null;
    }

    public static byte[] zzb(byte[] bArr) throws NoSuchAlgorithmException {
        byte[] digest;
        synchronized (zzyy) {
            MessageDigest zzaa = zzaa();
            if (zzaa != null) {
                zzaa.reset();
                zzaa.update(bArr);
                digest = zzyx.digest();
            } else {
                throw new NoSuchAlgorithmException("Cannot compute hash");
            }
        }
        return digest;
    }

    private static zzba zzc(long j) {
        zzba zzba = new zzba();
        zzba.zzds = Long.valueOf(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        return zzba;
    }

    static void zzz() {
        synchronized (zzyz) {
            if (!zzyw) {
                zzyw = true;
                new Thread(new zzbz()).start();
            }
        }
    }
}

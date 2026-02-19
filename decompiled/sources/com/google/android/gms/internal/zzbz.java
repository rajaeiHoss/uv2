package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class zzbz implements Runnable {
    zzbz() {
    }

    public final void run() {
        try {
            MessageDigest unused = zzbx.zzyx = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException unused2) {
        } catch (Throwable th) {
            zzbx.zzza.countDown();
            throw th;
        }
        zzbx.zzza.countDown();
    }
}

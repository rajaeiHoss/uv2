package com.google.android.gms.internal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@zzabh
public abstract class zzhs {
    private static MessageDigest zzbac;
    protected Object mLock = new Object();

    /* access modifiers changed from: protected */
    public final MessageDigest zzhh() {
        synchronized (this.mLock) {
            MessageDigest messageDigest = zzbac;
            if (messageDigest != null) {
                return messageDigest;
            }
            for (int i = 0; i < 2; i++) {
                try {
                    zzbac = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            MessageDigest messageDigest2 = zzbac;
            return messageDigest2;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract byte[] zzy(String str);
}

package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class zzes {
    private static String TAG = "zzes";
    private final String className;
    private final zzdm zzagq;
    private final String zzaks;
    private final int zzakt = 2;
    private volatile Method zzaku = null;
    private final Class<?>[] zzakv;
    private CountDownLatch zzakw = new CountDownLatch(1);

    public zzes(zzdm zzdm, String str, String str2, Class<?>... clsArr) {
        this.zzagq = zzdm;
        this.className = str;
        this.zzaks = str2;
        this.zzakv = clsArr;
        zzdm.getExecutorService().submit(new zzet(this));
    }

    /* access modifiers changed from: private */
    public final void zzba() {
        try {
            Class loadClass = this.zzagq.zzaf().loadClass(zzc(this.zzagq.zzah(), this.className));
            if (loadClass != null) {
                this.zzaku = loadClass.getMethod(zzc(this.zzagq.zzah(), this.zzaks), this.zzakv);
                Method method = this.zzaku;
            }
        } catch (zzcy | UnsupportedEncodingException | ClassNotFoundException | NoSuchMethodException | NullPointerException unused) {
        } catch (Throwable th) {
            this.zzakw.countDown();
            throw th;
        }
        this.zzakw.countDown();
    }

    private final String zzc(byte[] bArr, String str) throws zzcy, UnsupportedEncodingException {
        return new String(this.zzagq.zzag().zzb(bArr, str), "UTF-8");
    }

    public final Method zzbb() {
        if (this.zzaku != null) {
            return this.zzaku;
        }
        try {
            if (!this.zzakw.await(2, TimeUnit.SECONDS)) {
                return null;
            }
            return this.zzaku;
        } catch (InterruptedException unused) {
            return null;
        }
    }
}

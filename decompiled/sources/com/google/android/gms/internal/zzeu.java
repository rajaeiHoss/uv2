package com.google.android.gms.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class zzeu implements Callable {
    private String TAG = getClass().getSimpleName();
    private String className;
    protected final zzdm zzagq;
    protected final zzba zzakm;
    private String zzaks;
    protected Method zzaku;
    private int zzaky;
    private int zzakz;

    public zzeu(zzdm zzdm, String str, String str2, zzba zzba, int i, int i2) {
        this.zzagq = zzdm;
        this.className = str;
        this.zzaks = str2;
        this.zzakm = zzba;
        this.zzaky = i;
        this.zzakz = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void zzaw() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: zzay */
    public Void call() throws Exception {
        int i;
        try {
            long nanoTime = System.nanoTime();
            Method zza = this.zzagq.zza(this.className, this.zzaks);
            this.zzaku = zza;
            if (zza == null) {
                return null;
            }
            zzaw();
            zzcp zzaj = this.zzagq.zzaj();
            if (!(zzaj == null || (i = this.zzaky) == Integer.MIN_VALUE)) {
                zzaj.zza(this.zzakz, i, (System.nanoTime() - nanoTime) / 1000);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

final class zzcmp implements Runnable {
    private /* synthetic */ String zziuw;
    private /* synthetic */ zzcif zzjpj;
    private /* synthetic */ String zzjpm;
    private /* synthetic */ String zzjpn;
    private /* synthetic */ boolean zzjqb;
    private /* synthetic */ zzcme zzjri;
    private /* synthetic */ AtomicReference zzjrj;

    zzcmp(zzcme zzcme, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzcif zzcif) {
        this.zzjri = zzcme;
        this.zzjrj = atomicReference;
        this.zziuw = str;
        this.zzjpm = str2;
        this.zzjpn = str3;
        this.zzjqb = z;
        this.zzjpj = zzcif;
    }

    public final void run() {
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        List<zzcnl> zza;
        synchronized (this.zzjrj) {
            try {
                zzcjb zzd = this.zzjri.zzjrc;
                if (zzd == null) {
                    this.zzjri.zzayp().zzbau().zzd("Failed to get user properties", zzcjj.zzjs(this.zziuw), this.zzjpm, this.zzjpn);
                    this.zzjrj.set(Collections.emptyList());
                    this.zzjrj.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zziuw)) {
                    atomicReference2 = this.zzjrj;
                    zza = zzd.zza(this.zzjpm, this.zzjpn, this.zzjqb, this.zzjpj);
                } else {
                    atomicReference2 = this.zzjrj;
                    zza = zzd.zza(this.zziuw, this.zzjpm, this.zzjpn, this.zzjqb);
                }
                atomicReference2.set(zza);
                this.zzjri.zzyw();
                atomicReference = this.zzjrj;
                atomicReference.notify();
            } catch (RemoteException e) {
                try {
                    this.zzjri.zzayp().zzbau().zzd("Failed to get user properties", zzcjj.zzjs(this.zziuw), this.zzjpm, e);
                    this.zzjrj.set(Collections.emptyList());
                    atomicReference = this.zzjrj;
                } catch (Throwable th) {
                    this.zzjrj.notify();
                    throw th;
                }
            }
        }
    }
}

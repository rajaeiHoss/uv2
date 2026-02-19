package com.google.android.gms.internal;

import android.text.TextUtils;

public class zzbdo {
    private final String zzeqz;
    protected final zzbei zzeui;
    private zzbem zzfmb;

    protected zzbdo(String str, String str2, String str3) {
        zzbdw.zzfv(str);
        this.zzeqz = str;
        this.zzeui = new zzbei(str2);
        setSessionLabel(str3);
    }

    public final String getNamespace() {
        return this.zzeqz;
    }

    public final void setSessionLabel(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.zzeui.zzga(str);
        }
    }

    public final void zza(zzbem zzbem) {
        this.zzfmb = zzbem;
        if (zzbem == null) {
            zzagm();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(String str, long j, String str2) throws IllegalStateException {
        this.zzfmb.zza(this.zzeqz, str, j, (String) null);
    }

    public void zzagm() {
    }

    /* access modifiers changed from: protected */
    public final long zzagn() {
        return this.zzfmb.zzadw();
    }

    public void zzc(long j, int i) {
    }

    public void zzfu(String str) {
    }
}

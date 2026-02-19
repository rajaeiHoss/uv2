package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

final class zzeis implements Callable<List<? extends zzell>> {
    private /* synthetic */ zzegu zzneb;
    private /* synthetic */ long zzngk;
    private /* synthetic */ boolean zznia;
    private /* synthetic */ zzenn zznib;
    private /* synthetic */ zzenn zznic;
    private /* synthetic */ boolean zznid;
    private /* synthetic */ zzeir zznie;

    zzeis(zzeir zzeir, boolean z, zzegu zzegu, zzenn zzenn, long j, zzenn zzenn2, boolean z2) {
        this.zznie = zzeir;
        this.zznia = z;
        this.zzneb = zzegu;
        this.zznib = zzenn;
        this.zzngk = j;
        this.zznic = zzenn2;
        this.zznid = z2;
    }

    public final List<? extends zzell> call() throws Exception {
        if (this.zznia) {
            this.zznie.zznhs.zza(this.zzneb, this.zznib, this.zzngk);
        }
        this.zznie.zznhu.zza(this.zzneb, this.zznic, Long.valueOf(this.zzngk), this.zznid);
        return !this.zznid ? Collections.emptyList() : this.zznie.zza((zzejy) new zzekc(zzeka.zznjw, this.zzneb, this.zznic));
    }
}

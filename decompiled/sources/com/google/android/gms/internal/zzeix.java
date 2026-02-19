package com.google.android.gms.internal;

import java.util.List;

final class zzeix extends zzeeb<zzemq, zzekw<zzeiq>> {
    private /* synthetic */ List zznfs;
    private /* synthetic */ zzeir zznie;
    private /* synthetic */ zzenn zznij;
    private /* synthetic */ zzejt zznik;
    private /* synthetic */ zzejy zznil;

    zzeix(zzeir zzeir, zzenn zzenn, zzejt zzejt, zzejy zzejy, List list) {
        this.zznie = zzeir;
        this.zznij = zzenn;
        this.zznik = zzejt;
        this.zznil = zzejy;
        this.zznfs = list;
    }

    public final void zzh(zzemq zzemq, zzekw<zzeiq> zzekw) {
        zzenn zzenn = this.zznij;
        zzenn zzm = zzenn != null ? zzenn.zzm(zzemq) : null;
        zzejt zzb = this.zznik.zzb(zzemq);
        zzejy zzc = this.zznil.zzc(zzemq);
        if (zzc != null) {
            this.zznfs.addAll(this.zznie.zzb(zzc, zzekw, zzm, zzb));
        }
    }
}

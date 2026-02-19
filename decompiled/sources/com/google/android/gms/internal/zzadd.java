package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaj;

final class zzadd implements zzami<zzaj> {
    private /* synthetic */ zzadc zzcvh;

    zzadd(zzadc zzadc) {
        this.zzcvh = zzadc;
    }

    public final void zze(zzaj zzaj) {
        try {
            zzaj.zzb("AFMA_getAdapterLessMediationAd", this.zzcvh.zzcvf);
        } catch (Exception e) {
            zzahw.zzb("Error requesting an ad url", e);
            zzada.zzcvb.zzat(this.zzcvh.zzcvg);
        }
    }
}

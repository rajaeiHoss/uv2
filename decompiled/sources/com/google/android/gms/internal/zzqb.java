package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.lang.ref.WeakReference;

final class zzqb {
    /* access modifiers changed from: private */
    public final WeakReference<zzaof> zzbzc;
    /* access modifiers changed from: private */
    public String zzbzd;

    public zzqb(zzaof zzaof) {
        this.zzbzc = new WeakReference<>(zzaof);
    }

    public final void zza(zzaan zzaan) {
        zzaan.zza("/loadHtml", new zzqc(this, zzaan));
        zzaan.zza("/showOverlay", new zzqe(this, zzaan));
        zzaan.zza("/hideOverlay", new zzqf(this, zzaan));
        zzaof zzaof = (zzaof) this.zzbzc.get();
        if (zzaof != null) {
            zzaof.zzua().zza("/sendMessageToSdk", (zzt<? super zzaof>) new zzqg(this, zzaan));
        }
    }
}

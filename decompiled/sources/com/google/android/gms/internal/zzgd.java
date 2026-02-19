package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import org.json.JSONObject;

@zzabh
public final class zzgd implements zzgq {
    /* access modifiers changed from: private */
    public final zzfv zzawv;
    private final zzaof zzaww;
    private final zzt<zzaof> zzawx;
    private final zzt<zzaof> zzawy;
    private final zzt<zzaof> zzawz;

    public zzgd(zzfv zzfv, zzaof zzaof) {
        zzge zzge = new zzge(this);
        this.zzawx = zzge;
        zzgf zzgf = new zzgf(this);
        this.zzawy = zzgf;
        zzgg zzgg = new zzgg(this);
        this.zzawz = zzgg;
        this.zzawv = zzfv;
        this.zzaww = zzaof;
        zzaof.zza("/updateActiveView", (zzt<? super zzaof>) zzge);
        zzaof.zza("/untrackActiveViewUnit", (zzt<? super zzaof>) zzgf);
        zzaof.zza("/visibilityChanged", (zzt<? super zzaof>) zzgg);
        String valueOf = String.valueOf(zzfv.zzavy.zzfy());
        zzahw.zzby(valueOf.length() != 0 ? "Custom JS tracking ad unit: ".concat(valueOf) : new String("Custom JS tracking ad unit: "));
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        if (!z) {
            this.zzaww.zzb("AFMA_updateActiveView", jSONObject);
        } else {
            this.zzawv.zzb(this);
        }
    }

    public final boolean zzgk() {
        return true;
    }

    public final void zzgl() {
        zzaof zzaof = this.zzaww;
        zzaof.zzb("/visibilityChanged", (zzt<? super zzaof>) this.zzawz);
        zzaof.zzb("/untrackActiveViewUnit", (zzt<? super zzaof>) this.zzawy);
        zzaof.zzb("/updateActiveView", (zzt<? super zzaof>) this.zzawx);
    }
}

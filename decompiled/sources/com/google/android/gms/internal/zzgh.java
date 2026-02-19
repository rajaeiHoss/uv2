package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.ads.internal.gmsg.zzz;
import com.google.android.gms.ads.internal.js.zzaa;
import com.google.android.gms.ads.internal.js.zzaj;
import com.google.android.gms.ads.internal.js.zzn;
import com.google.android.gms.ads.internal.zzbt;
import org.json.JSONObject;

@zzabh
public final class zzgh implements zzgq {
    private final Context mContext;
    /* access modifiers changed from: private */
    public final zzfv zzawv;
    private final zzt<zzaj> zzawx = new zzgm(this);
    private final zzt<zzaj> zzawy = new zzgn(this);
    private final zzt<zzaj> zzawz = new zzgo(this);
    /* access modifiers changed from: private */
    public final zzz zzaxb;
    private zzaa zzaxc;
    /* access modifiers changed from: private */
    public boolean zzaxd;
    private final zzt<zzaj> zzaxe = new zzgp(this);

    public zzgh(zzfv zzfv, zzn zzn, Context context) {
        this.zzawv = zzfv;
        this.mContext = context;
        this.zzaxb = new zzz(context);
        zzaa zzb = zzn.zzb((zzcv) null);
        this.zzaxc = zzb;
        zzb.zza(new zzgi(this), new zzgj(this));
        String valueOf = String.valueOf(zzfv.zzavy.zzfy());
        zzahw.zzby(valueOf.length() != 0 ? "Core JS tracking ad unit: ".concat(valueOf) : new String("Core JS tracking ad unit: "));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzaj zzaj) {
        zzaj.zza("/updateActiveView", this.zzawx);
        zzaj.zza("/untrackActiveViewUnit", this.zzawy);
        zzaj.zza("/visibilityChanged", this.zzawz);
        if (zzbt.zzfh().zzq(this.mContext)) {
            zzaj.zza("/logScionEvent", this.zzaxe);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzaj zzaj) {
        zzaj.zzb("/visibilityChanged", this.zzawz);
        zzaj.zzb("/untrackActiveViewUnit", this.zzawy);
        zzaj.zzb("/updateActiveView", this.zzawx);
        if (zzbt.zzfh().zzq(this.mContext)) {
            zzaj.zzb("/logScionEvent", this.zzaxe);
        }
    }

    public final void zzb(JSONObject jSONObject, boolean z) {
        this.zzaxc.zza(new zzgk(this, jSONObject), new zzamh());
    }

    public final boolean zzgk() {
        return this.zzaxd;
    }

    public final void zzgl() {
        this.zzaxc.zza(new zzgl(this), new zzamh());
        this.zzaxc.release();
    }
}

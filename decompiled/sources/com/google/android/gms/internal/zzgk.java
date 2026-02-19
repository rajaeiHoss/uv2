package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzaj;
import org.json.JSONObject;

final class zzgk implements zzami<zzaj> {
    private /* synthetic */ JSONObject zzaxg;

    zzgk(zzgh zzgh, JSONObject jSONObject) {
        this.zzaxg = jSONObject;
    }

    public final void zze(zzaj zzaj) {
        zzaj.zzb("AFMA_updateActiveView", this.zzaxg);
    }
}

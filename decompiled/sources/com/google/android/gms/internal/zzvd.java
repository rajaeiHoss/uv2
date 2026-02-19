package com.google.android.gms.internal;

import org.json.JSONObject;

final /* synthetic */ class zzvd implements zzuz {
    static final zzuz zzcgo = new zzvd();

    private zzvd() {
    }

    public final Object zze(JSONObject jSONObject) {
        try {
            return zzvc.zzf(jSONObject);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

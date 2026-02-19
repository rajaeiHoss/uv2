package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzald;
import com.google.android.gms.internal.zzali;
import com.google.android.gms.internal.zzalt;
import org.json.JSONObject;

final /* synthetic */ class zzad implements zzald {
    static final zzald zzaoy = new zzad();

    private zzad() {
    }

    public final zzalt zzc(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            String appSettingsJson = jSONObject.optString("appSettingsJson", "");
            if (!appSettingsJson.isEmpty()) {
                zzbt.zzep().zzqe().zzcg(appSettingsJson);
            }
        }
        return zzali.zzh(null);
    }
}

package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final /* synthetic */ class zzqd implements zzapv {
    private final zzqc zzbzg;
    private final Map zzbzh;
    private final zzaan zzbzi;

    zzqd(zzqc zzqc, Map map, zzaan zzaan) {
        this.zzbzg = zzqc;
        this.zzbzh = map;
        this.zzbzi = zzaan;
    }

    public final void zza(zzaof zzaof, boolean z) {
        zzqc zzqc = this.zzbzg;
        Map map = this.zzbzh;
        zzaan zzaan = this.zzbzi;
        String unused = zzqc.zzbzf.zzbzd = (String) map.get("id");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("messageType", "htmlLoaded");
            jSONObject.put("id", zzqc.zzbzf.zzbzd);
            zzaan.zza("sendMessageToNativeJs", jSONObject);
        } catch (JSONException e) {
            zzahw.zzb("Unable to dispatch sendMessageToNativeJs event", e);
        }
    }
}

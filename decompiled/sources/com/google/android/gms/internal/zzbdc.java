package com.google.android.gms.internal;

import com.google.android.gms.common.util.zzq;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbdc {
    private final int zzexd;
    private final String zzfld;
    private final JSONObject zzfls;

    private zzbdc(String str, int i, JSONObject jSONObject) {
        this.zzfld = str;
        this.zzexd = i;
        this.zzfls = jSONObject;
    }

    public zzbdc(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("playerId"), jSONObject.optInt("playerState"), jSONObject.optJSONObject("playerData"));
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof zzbdc)) {
            zzbdc zzbdc = (zzbdc) obj;
            return this.zzexd == zzbdc.zzexd && zzbdw.zza(this.zzfld, zzbdc.zzfld) && zzq.zzc(this.zzfls, zzbdc.zzfls);
        }
        return false;
    }

    public final JSONObject getPlayerData() {
        return this.zzfls;
    }

    public final String getPlayerId() {
        return this.zzfld;
    }

    public final int getPlayerState() {
        return this.zzexd;
    }
}

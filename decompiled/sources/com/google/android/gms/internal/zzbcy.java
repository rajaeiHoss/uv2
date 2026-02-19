package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

public final class zzbcy {
    private final String zzflg;
    private final int zzflh;
    private final String zzfli;

    private zzbcy(String str, int i, String str2) {
        this.zzflg = str;
        this.zzflh = i;
        this.zzfli = str2;
    }

    public zzbcy(JSONObject jSONObject) throws JSONException {
        this(jSONObject.optString("applicationName"), jSONObject.optInt("maxPlayers"), jSONObject.optString("version"));
    }

    public final int getMaxPlayers() {
        return this.zzflh;
    }

    public final String getVersion() {
        return this.zzfli;
    }

    public final String zzagk() {
        return this.zzflg;
    }
}

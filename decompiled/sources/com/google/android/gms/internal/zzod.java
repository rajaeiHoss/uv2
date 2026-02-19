package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

final class zzod extends zzny<String> {
    zzod(int i, String str, String str2) {
        super(i, str, str2, (zznz) null);
    }

    public final String zza(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(getKey(), (String) zzje());
    }

    public final void zza(SharedPreferences.Editor editor, String str) {
        editor.putString(getKey(), str);
    }

    public final String zzb(JSONObject jSONObject) {
        return jSONObject.optString(getKey(), (String) zzje());
    }
}

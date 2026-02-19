package com.google.android.gms.internal;

import android.content.SharedPreferences;
import org.json.JSONObject;

final class zznz extends zzny<Boolean> {
    zznz(int i, String str, Boolean bool) {
        super(i, str, bool, (zznz) null);
    }

    public final Boolean zza(SharedPreferences sharedPreferences) {
        return Boolean.valueOf(sharedPreferences.getBoolean(getKey(), ((Boolean) zzje()).booleanValue()));
    }

    public final void zza(SharedPreferences.Editor editor, Boolean bool) {
        editor.putBoolean(getKey(), bool.booleanValue());
    }

    public final Boolean zzb(JSONObject jSONObject) {
        return Boolean.valueOf(jSONObject.optBoolean(getKey(), ((Boolean) zzje()).booleanValue()));
    }
}

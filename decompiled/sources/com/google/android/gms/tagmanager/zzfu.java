package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;

final class zzfu {
    static void zze(Context context, String str, String str2, String str3) {
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString(str2, str3);
        edit.apply();
    }
}

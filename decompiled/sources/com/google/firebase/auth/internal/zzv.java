package com.google.firebase.auth.internal;

import android.content.Context;
import com.google.android.gms.internal.zzcce;
import com.google.android.gms.internal.zzccp;

public final class zzv {
    public static final zzcce<Boolean> zzmue = zzcce.zzb(0, "firebase_auth_proactive_token_refresh_enabled", (Boolean) true);

    public static final void initialize(Context context) {
        zzccp.zzasn();
        zzccp.zzaso().initialize(context);
    }
}

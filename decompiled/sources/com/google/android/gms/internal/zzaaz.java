package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzaaz implements zzalf<zzaof> {
    private /* synthetic */ String val$message;
    private /* synthetic */ JSONObject zzcqu;

    zzaaz(zzaar zzaar, String str, JSONObject jSONObject) {
        this.val$message = str;
        this.zzcqu = jSONObject;
    }

    public final void onSuccess(zzaof zzaof) {
        zzaof.zza(this.val$message, this.zzcqu);
    }

    public final void zzb(Throwable th) {
    }
}

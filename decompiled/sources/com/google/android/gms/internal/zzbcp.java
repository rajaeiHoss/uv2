package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzbcp extends zzbcr {
    private /* synthetic */ zzbcl zzfkt;
    private /* synthetic */ String zzfkw;
    private /* synthetic */ JSONObject zzfkx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbcp(zzbcl zzbcl, String str, JSONObject jSONObject) {
        super(zzbcl);
        this.zzfkt = zzbcl;
        this.zzfkw = str;
        this.zzfkx = jSONObject;
    }

    public final void execute() {
        this.zzfkt.zza(this.zzfkw, 6, this.zzfkx, this.zzezb);
    }
}

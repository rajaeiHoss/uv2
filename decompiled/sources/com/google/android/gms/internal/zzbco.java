package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzbco extends zzbcr {
    private /* synthetic */ zzbcl zzfkt;
    private /* synthetic */ int zzfkv;
    private /* synthetic */ String zzfkw;
    private /* synthetic */ JSONObject zzfkx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbco(zzbcl zzbcl, int i, String str, JSONObject jSONObject) {
        super(zzbcl);
        this.zzfkt = zzbcl;
        this.zzfkv = i;
        this.zzfkw = str;
        this.zzfkx = jSONObject;
    }

    public final void execute() {
        int i = this.zzfkv;
        int i2 = 5;
        if (i != 2) {
            i2 = i != 3 ? i != 4 ? i != 5 ? i != 6 ? 0 : 4 : 3 : 2 : 1;
        }
        if (i2 == 0) {
            this.zzezb.zza(-1, 2001, (Object) null);
            zzbcl.zzeus.zzf("sendPlayerRequest for unsupported playerState: %d", Integer.valueOf(this.zzfkv));
            return;
        }
        this.zzfkt.zza(this.zzfkw, i2, this.zzfkx, this.zzezb);
    }
}

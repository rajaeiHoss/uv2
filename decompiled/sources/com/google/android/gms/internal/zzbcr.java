package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

public abstract class zzbcr extends zzbct<GameManagerClient.GameManagerResult> {
    final /* synthetic */ zzbcl zzfkt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbcr(zzbcl zzbcl) {
        super(zzbcl);
        this.zzfkt = zzbcl;
        this.zzezb = new zzbcs(this, zzbcl);
    }

    public static GameManagerClient.GameManagerResult zzl(Status status) {
        return new zzbcx(status, (String) null, -1, (JSONObject) null);
    }

    public final GameManagerClient.GameManagerResult zzb(Status status) {
        return zzl(status);
    }
}

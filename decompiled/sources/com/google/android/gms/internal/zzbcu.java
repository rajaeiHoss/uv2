package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;

public abstract class zzbcu extends zzbct<GameManagerClient.GameManagerInstanceResult> {
    final /* synthetic */ zzbcl zzfkt;
    /* access modifiers changed from: private */
    public GameManagerClient zzflb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbcu(zzbcl zzbcl, GameManagerClient gameManagerClient) {
        super(zzbcl);
        this.zzfkt = zzbcl;
        this.zzflb = gameManagerClient;
        this.zzezb = new zzbcv(this, zzbcl);
    }

    public static GameManagerClient.GameManagerInstanceResult zzm(Status status) {
        return new zzbcw(status, (GameManagerClient) null);
    }

    public final GameManagerClient.GameManagerInstanceResult zzb(Status status) {
        return zzm(status);
    }
}

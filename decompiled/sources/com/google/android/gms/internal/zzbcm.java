package com.google.android.gms.internal;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;
import org.json.JSONObject;

final class zzbcm extends zzbcu {
    final /* synthetic */ zzbcl zzfkt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbcm(zzbcl zzbcl, GameManagerClient gameManagerClient) {
        super(zzbcl, gameManagerClient);
        this.zzfkt = zzbcl;
    }

    public final void execute() {
        try {
            this.zzfkt.zzfam.setMessageReceivedCallbacks(this.zzfkt.zzffo, this.zzfkt.getNamespace(), new zzbcn(this));
            this.zzfkt.zzagi();
            this.zzfkt.zzagh();
            this.zzfkt.zza((String) null, 1100, (JSONObject) null, this.zzezb);
        } catch (IOException | IllegalStateException unused) {
            this.zzezb.zza(-1, 8, (Object) null);
        }
    }
}

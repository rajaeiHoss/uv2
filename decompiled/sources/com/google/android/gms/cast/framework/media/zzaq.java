package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import java.util.Set;
import java.util.TimerTask;

final class zzaq extends TimerTask {
    private /* synthetic */ RemoteMediaClient zzfgb;
    private /* synthetic */ RemoteMediaClient.zze zzfgh;

    zzaq(RemoteMediaClient.zze zze, RemoteMediaClient remoteMediaClient) {
        this.zzfgh = zze;
        this.zzfgb = remoteMediaClient;
    }

    public final void run() {
        this.zzfgb.zza((Set<RemoteMediaClient.ProgressListener>) this.zzfgh.zzfgd);
        this.zzfgb.mHandler.postDelayed(this, this.zzfgh.zzfge);
    }
}

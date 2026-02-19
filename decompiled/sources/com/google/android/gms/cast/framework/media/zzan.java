package com.google.android.gms.cast.framework.media;

import android.util.Log;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzben;
import org.json.JSONObject;

final class zzan implements zzben {
    private /* synthetic */ RemoteMediaClient zzfgb;
    private /* synthetic */ RemoteMediaClient.zzb zzfgc;

    zzan(RemoteMediaClient.zzb zzb, RemoteMediaClient remoteMediaClient) {
        this.zzfgc = zzb;
        this.zzfgb = remoteMediaClient;
    }

    public final void zza(long j, int i, Object obj) {
        try {
            this.zzfgc.setResult(new RemoteMediaClient.zzc(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestCompleted", e);
        }
    }

    public final void zzx(long j) {
        try {
            RemoteMediaClient.zzb zzb = this.zzfgc;
            zzb.setResult((RemoteMediaClient.MediaChannelResult) zzb.zzb(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestReplaced", e);
        }
    }
}

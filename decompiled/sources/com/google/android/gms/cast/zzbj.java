package com.google.android.gms.cast;

import android.util.Log;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzben;
import org.json.JSONObject;

final class zzbj implements zzben {
    private /* synthetic */ RemoteMediaPlayer.zzb zzezc;

    zzbj(RemoteMediaPlayer.zzb zzb) {
        this.zzezc = zzb;
    }

    public final void zza(long j, int i, Object obj) {
        try {
            this.zzezc.setResult(new RemoteMediaPlayer.zzc(new Status(i), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestCompleted", e);
        }
    }

    public final void zzx(long j) {
        try {
            RemoteMediaPlayer.zzb zzb = this.zzezc;
            zzb.setResult((RemoteMediaPlayer.MediaChannelResult) zzb.zzb(new Status(2103)));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaPlayer", "Result already set when calling onRequestReplaced", e);
        }
    }
}

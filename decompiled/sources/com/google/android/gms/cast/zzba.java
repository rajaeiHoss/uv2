package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzba extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ int zzeyr;
    private /* synthetic */ int zzeys;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, GoogleApiClient googleApiClient2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeyr = i;
        this.zzeys = i2;
        this.zzeya = googleApiClient2;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        r0 = r7.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00b8, code lost:
        r7.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c1, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r8) {
        /*
            r7 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r8 = r7.zzexz
            java.lang.Object r8 = r8.mLock
            monitor-enter(r8)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            int r1 = r7.zzeyr     // Catch:{ all -> 0x00c2 }
            int r0 = r0.zzbc(r1)     // Catch:{ all -> 0x00c2 }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00c2 }
            r0.<init>(r2)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00c2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00c2 }
            return
        L_0x0023:
            int r1 = r7.zzeys     // Catch:{ all -> 0x00c2 }
            r3 = 1
            if (r1 >= 0) goto L_0x004c
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00c2 }
            r1 = 2001(0x7d1, float:2.804E-42)
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ all -> 0x00c2 }
            java.lang.String r5 = "Invalid request: Invalid newIndex %d."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x00c2 }
            int r6 = r7.zzeys     // Catch:{ all -> 0x00c2 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x00c2 }
            r3[r2] = r6     // Catch:{ all -> 0x00c2 }
            java.lang.String r2 = java.lang.String.format(r4, r5, r3)     // Catch:{ all -> 0x00c2 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00c2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00c2 }
            return
        L_0x004c:
            if (r0 != r1) goto L_0x005e
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x00c2 }
            r0.<init>(r2)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x00c2 }
            r7.setResult(r0)     // Catch:{ all -> 0x00c2 }
            monitor-exit(r8)     // Catch:{ all -> 0x00c2 }
            return
        L_0x005e:
            if (r1 <= r0) goto L_0x0062
            int r1 = r1 + 1
        L_0x0062:
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.MediaStatus r0 = r0.getMediaStatus()     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getQueueItem(r1)     // Catch:{ all -> 0x00c2 }
            if (r0 == 0) goto L_0x0073
            int r0 = r0.getItemId()     // Catch:{ all -> 0x00c2 }
            goto L_0x0074
        L_0x0073:
            r0 = 0
        L_0x0074:
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.common.api.GoogleApiClient r4 = r7.zzeya     // Catch:{ all -> 0x00c2 }
            r1.zzb(r4)     // Catch:{ all -> 0x00c2 }
            r1 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r4 = r7.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            com.google.android.gms.internal.zzbej r4 = r4.zzext     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            com.google.android.gms.internal.zzben r5 = r7.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            int[] r3 = new int[r3]     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            int r6 = r7.zzeyr     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            r3[r2] = r6     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            org.json.JSONObject r2 = r7.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            r4.zza((com.google.android.gms.internal.zzben) r5, (int[]) r3, (int) r0, (org.json.JSONObject) r2)     // Catch:{ zzbel | IllegalStateException -> 0x009f }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x00c2 }
        L_0x0099:
            r0.zzb(r1)     // Catch:{ all -> 0x00c2 }
            goto L_0x00b6
        L_0x009d:
            r0 = move-exception
            goto L_0x00b8
        L_0x009f:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x009d }
            r2 = 2100(0x834, float:2.943E-42)
            r0.<init>(r2)     // Catch:{ all -> 0x009d }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x009d }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x009d }
            r7.setResult(r0)     // Catch:{ all -> 0x009d }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x00c2 }
            goto L_0x0099
        L_0x00b6:
            monitor-exit(r8)     // Catch:{ all -> 0x00c2 }
            return
        L_0x00b8:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r7.zzexz     // Catch:{ all -> 0x00c2 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x00c2 }
            r2.zzb(r1)     // Catch:{ all -> 0x00c2 }
            throw r0     // Catch:{ all -> 0x00c2 }
        L_0x00c2:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x00c2 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzba.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

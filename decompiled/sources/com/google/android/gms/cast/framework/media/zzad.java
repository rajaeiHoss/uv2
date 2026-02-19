package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzad extends RemoteMediaClient.zzb {
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ int zzeyr;
    private /* synthetic */ int zzeys;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzad(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyr = i;
        this.zzeys = i2;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:(1:18)|19|(1:21)(1:22)|23|24|25|26) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0088 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r8) {
        /*
            r7 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r8 = r7.zzfft
            java.lang.Object r8 = r8.mLock
            monitor-enter(r8)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r7.zzfft     // Catch:{ all -> 0x009a }
            int r1 = r7.zzeyr     // Catch:{ all -> 0x009a }
            int r0 = r0.zzbc(r1)     // Catch:{ all -> 0x009a }
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x009a }
            r0.<init>(r2)     // Catch:{ all -> 0x009a }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x009a }
            r7.setResult(r0)     // Catch:{ all -> 0x009a }
            monitor-exit(r8)     // Catch:{ all -> 0x009a }
            return
        L_0x0023:
            int r1 = r7.zzeys     // Catch:{ all -> 0x009a }
            r3 = 1
            if (r1 >= 0) goto L_0x004c
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x009a }
            r1 = 2001(0x7d1, float:2.804E-42)
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ all -> 0x009a }
            java.lang.String r5 = "Invalid request: Invalid newIndex %d."
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x009a }
            int r6 = r7.zzeys     // Catch:{ all -> 0x009a }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x009a }
            r3[r2] = r6     // Catch:{ all -> 0x009a }
            java.lang.String r2 = java.lang.String.format(r4, r5, r3)     // Catch:{ all -> 0x009a }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x009a }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x009a }
            r7.setResult(r0)     // Catch:{ all -> 0x009a }
            monitor-exit(r8)     // Catch:{ all -> 0x009a }
            return
        L_0x004c:
            if (r0 != r1) goto L_0x005e
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x009a }
            r0.<init>(r2)     // Catch:{ all -> 0x009a }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x009a }
            r7.setResult(r0)     // Catch:{ all -> 0x009a }
            monitor-exit(r8)     // Catch:{ all -> 0x009a }
            return
        L_0x005e:
            if (r1 <= r0) goto L_0x0062
            int r1 = r1 + 1
        L_0x0062:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r7.zzfft     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.MediaStatus r0 = r0.getMediaStatus()     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getQueueItem(r1)     // Catch:{ all -> 0x009a }
            if (r0 == 0) goto L_0x0073
            int r0 = r0.getItemId()     // Catch:{ all -> 0x009a }
            goto L_0x0074
        L_0x0073:
            r0 = 0
        L_0x0074:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r1 = r7.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            com.google.android.gms.internal.zzbej r1 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            com.google.android.gms.internal.zzben r4 = r7.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            int[] r3 = new int[r3]     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            int r5 = r7.zzeyr     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            r3[r2] = r5     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            org.json.JSONObject r2 = r7.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            r1.zza((com.google.android.gms.internal.zzben) r4, (int[]) r3, (int) r0, (org.json.JSONObject) r2)     // Catch:{ zzbel | IllegalStateException -> 0x0088 }
            goto L_0x0098
        L_0x0088:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x009a }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x009a }
            com.google.android.gms.common.api.Result r0 = r7.zzb(r0)     // Catch:{ all -> 0x009a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x009a }
            r7.setResult(r0)     // Catch:{ all -> 0x009a }
        L_0x0098:
            monitor-exit(r8)     // Catch:{ all -> 0x009a }
            return
        L_0x009a:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x009a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzad.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzac extends RemoteMediaClient.zzb {
    private /* synthetic */ long zzeyg;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ int zzeyr;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzac(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyr = i;
        this.zzeyg = j;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:8|9|10|11) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0038 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzfft
            java.lang.Object r11 = r11.mLock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzfft     // Catch:{ all -> 0x004a }
            int r1 = r10.zzeyr     // Catch:{ all -> 0x004a }
            int r0 = r0.zzbc(r1)     // Catch:{ all -> 0x004a }
            r1 = -1
            if (r0 != r1) goto L_0x0023
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004a }
            r1 = 0
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            com.google.android.gms.common.api.Result r0 = r10.zzb(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x004a }
            r10.setResult(r0)     // Catch:{ all -> 0x004a }
            monitor-exit(r11)     // Catch:{ all -> 0x004a }
            return
        L_0x0023:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            com.google.android.gms.internal.zzbej r1 = r0.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            com.google.android.gms.internal.zzben r2 = r10.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            int r3 = r10.zzeyr     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            long r4 = r10.zzeyg     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            r6 = 0
            r7 = 0
            r8 = 0
            org.json.JSONObject r9 = r10.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            r1.zza((com.google.android.gms.internal.zzben) r2, (int) r3, (long) r4, (com.google.android.gms.cast.MediaQueueItem[]) r6, (int) r7, (java.lang.Integer) r8, (org.json.JSONObject) r9)     // Catch:{ zzbel | IllegalStateException -> 0x0038 }
            goto L_0x0048
        L_0x0038:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004a }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            com.google.android.gms.common.api.Result r0 = r10.zzb(r0)     // Catch:{ all -> 0x004a }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x004a }
            r10.setResult(r0)     // Catch:{ all -> 0x004a }
        L_0x0048:
            monitor-exit(r11)     // Catch:{ all -> 0x004a }
            return
        L_0x004a:
            r0 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x004a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzac.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzt extends RemoteMediaClient.zzb {
    private /* synthetic */ long zzeyg;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ int zzeyj;
    private /* synthetic */ MediaQueueItem zzeyk;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzt(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyk = mediaQueueItem;
        this.zzeyj = i;
        this.zzeyg = j;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzfft
            java.lang.Object r11 = r11.mLock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            com.google.android.gms.internal.zzbej r1 = r0.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            com.google.android.gms.internal.zzben r2 = r10.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            r0 = 1
            com.google.android.gms.cast.MediaQueueItem[] r3 = new com.google.android.gms.cast.MediaQueueItem[r0]     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            r0 = 0
            com.google.android.gms.cast.MediaQueueItem r4 = r10.zzeyk     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            r3[r0] = r4     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            int r4 = r10.zzeyj     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            r5 = 0
            r6 = 0
            long r7 = r10.zzeyg     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            org.json.JSONObject r9 = r10.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            r1.zza((com.google.android.gms.internal.zzben) r2, (com.google.android.gms.cast.MediaQueueItem[]) r3, (int) r4, (int) r5, (int) r6, (long) r7, (org.json.JSONObject) r9)     // Catch:{ zzbel | IllegalStateException -> 0x0025 }
            goto L_0x0035
        L_0x0023:
            r0 = move-exception
            goto L_0x0037
        L_0x0025:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0023 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0023 }
            com.google.android.gms.common.api.Result r0 = r10.zzb(r0)     // Catch:{ all -> 0x0023 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0023 }
            r10.setResult(r0)     // Catch:{ all -> 0x0023 }
        L_0x0035:
            monitor-exit(r11)     // Catch:{ all -> 0x0023 }
            return
        L_0x0037:
            monitor-exit(r11)     // Catch:{ all -> 0x0023 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzt.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

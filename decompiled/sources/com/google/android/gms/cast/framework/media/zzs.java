package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzs extends RemoteMediaClient.zzb {
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ MediaQueueItem[] zzeyi;
    private /* synthetic */ int zzeyj;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyi = mediaQueueItemArr;
        this.zzeyj = i;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzfft
            java.lang.Object r11 = r11.mLock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            com.google.android.gms.internal.zzbej r1 = r0.zzext     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            com.google.android.gms.internal.zzben r2 = r10.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            com.google.android.gms.cast.MediaQueueItem[] r3 = r10.zzeyi     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            int r4 = r10.zzeyj     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            r5 = 0
            r6 = -1
            r7 = -1
            org.json.JSONObject r9 = r10.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            r1.zza((com.google.android.gms.internal.zzben) r2, (com.google.android.gms.cast.MediaQueueItem[]) r3, (int) r4, (int) r5, (int) r6, (long) r7, (org.json.JSONObject) r9)     // Catch:{ zzbel | IllegalStateException -> 0x001f }
            goto L_0x002f
        L_0x001d:
            r0 = move-exception
            goto L_0x0031
        L_0x001f:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x001d }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x001d }
            com.google.android.gms.common.api.Result r0 = r10.zzb(r0)     // Catch:{ all -> 0x001d }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x001d }
            r10.setResult(r0)     // Catch:{ all -> 0x001d }
        L_0x002f:
            monitor-exit(r11)     // Catch:{ all -> 0x001d }
            return
        L_0x0031:
            monitor-exit(r11)     // Catch:{ all -> 0x001d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzs.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzr extends RemoteMediaClient.zzb {
    private /* synthetic */ MediaQueueItem[] zzeyd;
    private /* synthetic */ int zzeye;
    private /* synthetic */ int zzeyf;
    private /* synthetic */ long zzeyg;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyd = mediaQueueItemArr;
        this.zzeye = i;
        this.zzeyf = i2;
        this.zzeyg = j;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r10) {
        /*
            r9 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r10 = r9.zzfft
            java.lang.Object r10 = r10.mLock
            monitor-enter(r10)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r9.zzfft     // Catch:{ IllegalStateException -> 0x001f }
            com.google.android.gms.internal.zzbej r1 = r0.zzext     // Catch:{ IllegalStateException -> 0x001f }
            com.google.android.gms.internal.zzben r2 = r9.zzezb     // Catch:{ IllegalStateException -> 0x001f }
            com.google.android.gms.cast.MediaQueueItem[] r3 = r9.zzeyd     // Catch:{ IllegalStateException -> 0x001f }
            int r4 = r9.zzeye     // Catch:{ IllegalStateException -> 0x001f }
            int r5 = r9.zzeyf     // Catch:{ IllegalStateException -> 0x001f }
            long r6 = r9.zzeyg     // Catch:{ IllegalStateException -> 0x001f }
            org.json.JSONObject r8 = r9.zzeyh     // Catch:{ IllegalStateException -> 0x001f }
            r1.zza(r2, r3, r4, r5, r6, r8)     // Catch:{ IllegalStateException -> 0x001f }
            goto L_0x002f
        L_0x001d:
            r0 = move-exception
            goto L_0x0031
        L_0x001f:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x001d }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x001d }
            com.google.android.gms.common.api.Result r0 = r9.zzb(r0)     // Catch:{ all -> 0x001d }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x001d }
            r9.setResult(r0)     // Catch:{ all -> 0x001d }
        L_0x002f:
            monitor-exit(r10)     // Catch:{ all -> 0x001d }
            return
        L_0x0031:
            monitor-exit(r10)     // Catch:{ all -> 0x001d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzr.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

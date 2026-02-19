package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzz extends RemoteMediaClient.zzb {
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ RemoteMediaClient zzfft;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzz(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r11) {
        /*
            r10 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r11 = r10.zzfft
            java.lang.Object r11 = r11.mLock
            monitor-enter(r11)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r10.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x001d }
            com.google.android.gms.internal.zzbej r1 = r0.zzext     // Catch:{ zzbel | IllegalStateException -> 0x001d }
            com.google.android.gms.internal.zzben r2 = r10.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x001d }
            r3 = 0
            r4 = -1
            r6 = 0
            r7 = 1
            r8 = 0
            org.json.JSONObject r9 = r10.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x001d }
            r1.zza((com.google.android.gms.internal.zzben) r2, (int) r3, (long) r4, (com.google.android.gms.cast.MediaQueueItem[]) r6, (int) r7, (java.lang.Integer) r8, (org.json.JSONObject) r9)     // Catch:{ zzbel | IllegalStateException -> 0x001d }
            goto L_0x002d
        L_0x001b:
            r0 = move-exception
            goto L_0x002f
        L_0x001d:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x001b }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x001b }
            com.google.android.gms.common.api.Result r0 = r10.zzb(r0)     // Catch:{ all -> 0x001b }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x001b }
            r10.setResult(r0)     // Catch:{ all -> 0x001b }
        L_0x002d:
            monitor-exit(r11)     // Catch:{ all -> 0x001b }
            return
        L_0x002f:
            monitor-exit(r11)     // Catch:{ all -> 0x001b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzz.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

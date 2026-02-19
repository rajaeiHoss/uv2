package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzy extends RemoteMediaClient.zzb {
    private /* synthetic */ MediaInfo zzeyq;
    private /* synthetic */ RemoteMediaClient zzfft;
    private /* synthetic */ MediaLoadOptions zzffu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzy(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzeyq = mediaInfo;
        this.zzffu = mediaLoadOptions;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r5) {
        /*
            r4 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r5 = r4.zzfft
            java.lang.Object r5 = r5.mLock
            monitor-enter(r5)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.zzfft     // Catch:{ IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.zzbej r0 = r0.zzext     // Catch:{ IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.zzben r1 = r4.zzezb     // Catch:{ IllegalStateException -> 0x0019 }
            com.google.android.gms.cast.MediaInfo r2 = r4.zzeyq     // Catch:{ IllegalStateException -> 0x0019 }
            com.google.android.gms.cast.MediaLoadOptions r3 = r4.zzffu     // Catch:{ IllegalStateException -> 0x0019 }
            r0.zza((com.google.android.gms.internal.zzben) r1, (com.google.android.gms.cast.MediaInfo) r2, (com.google.android.gms.cast.MediaLoadOptions) r3)     // Catch:{ IllegalStateException -> 0x0019 }
            goto L_0x0029
        L_0x0017:
            r0 = move-exception
            goto L_0x002b
        L_0x0019:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0017 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.common.api.Result r0 = r4.zzb(r0)     // Catch:{ all -> 0x0017 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0017 }
            r4.setResult(r0)     // Catch:{ all -> 0x0017 }
        L_0x0029:
            monitor-exit(r5)     // Catch:{ all -> 0x0017 }
            return
        L_0x002b:
            monitor-exit(r5)     // Catch:{ all -> 0x0017 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzy.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

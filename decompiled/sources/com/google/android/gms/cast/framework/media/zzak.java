package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;

final class zzak extends RemoteMediaClient.zzb {
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ RemoteMediaClient zzfft;
    private /* synthetic */ boolean zzffx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzak(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzfft = remoteMediaClient;
        this.zzffx = z;
        this.zzeyh = jSONObject;
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
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.zzfft     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.zzbej r0 = r0.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
            com.google.android.gms.internal.zzben r1 = r4.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
            boolean r2 = r4.zzffx     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
            org.json.JSONObject r3 = r4.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
            r0.zza((com.google.android.gms.internal.zzben) r1, (boolean) r2, (org.json.JSONObject) r3)     // Catch:{ zzbel | IllegalStateException -> 0x0019 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.zzak.zzb(com.google.android.gms.internal.zzbdp):void");
    }
}

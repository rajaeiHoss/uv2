package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzaw extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ long zzeyg;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ boolean zzeyo;
    private /* synthetic */ long[] zzeyp;
    private /* synthetic */ MediaInfo zzeyq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, boolean z, long j, long[] jArr, JSONObject jSONObject, MediaInfo mediaInfo) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyo = z;
        this.zzeyg = j;
        this.zzeyp = jArr;
        this.zzeyh = jSONObject;
        this.zzeyq = mediaInfo;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0 = r5.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0066, code lost:
        r5.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006f, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x004d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r6 = r5.zzexz
            java.lang.Object r6 = r6.mLock
            monitor-enter(r6)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r5.zzexz     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0070 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r5.zzeya     // Catch:{ all -> 0x0070 }
            r0.zzb(r1)     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions$Builder r0 = new com.google.android.gms.cast.MediaLoadOptions$Builder     // Catch:{ all -> 0x0070 }
            r0.<init>()     // Catch:{ all -> 0x0070 }
            boolean r1 = r5.zzeyo     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions$Builder r0 = r0.setAutoplay(r1)     // Catch:{ all -> 0x0070 }
            long r1 = r5.zzeyg     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions$Builder r0 = r0.setPlayPosition(r1)     // Catch:{ all -> 0x0070 }
            long[] r1 = r5.zzeyp     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions$Builder r0 = r0.setActiveTrackIds(r1)     // Catch:{ all -> 0x0070 }
            org.json.JSONObject r1 = r5.zzeyh     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions$Builder r0 = r0.setCustomData(r1)     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.MediaLoadOptions r0 = r0.build()     // Catch:{ all -> 0x0070 }
            r1 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r5.zzexz     // Catch:{ IllegalStateException -> 0x004d }
            com.google.android.gms.internal.zzbej r2 = r2.zzext     // Catch:{ IllegalStateException -> 0x004d }
            com.google.android.gms.internal.zzben r3 = r5.zzezb     // Catch:{ IllegalStateException -> 0x004d }
            com.google.android.gms.cast.MediaInfo r4 = r5.zzeyq     // Catch:{ IllegalStateException -> 0x004d }
            r2.zza((com.google.android.gms.internal.zzben) r3, (com.google.android.gms.cast.MediaInfo) r4, (com.google.android.gms.cast.MediaLoadOptions) r0)     // Catch:{ IllegalStateException -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r5.zzexz     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0070 }
        L_0x0047:
            r0.zzb(r1)     // Catch:{ all -> 0x0070 }
            goto L_0x0064
        L_0x004b:
            r0 = move-exception
            goto L_0x0066
        L_0x004d:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x004b }
            r2 = 2100(0x834, float:2.943E-42)
            r0.<init>(r2)     // Catch:{ all -> 0x004b }
            com.google.android.gms.common.api.Result r0 = r5.zzb(r0)     // Catch:{ all -> 0x004b }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r0 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r0     // Catch:{ all -> 0x004b }
            r5.setResult(r0)     // Catch:{ all -> 0x004b }
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r5.zzexz     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0070 }
            goto L_0x0047
        L_0x0064:
            monitor-exit(r6)     // Catch:{ all -> 0x0070 }
            return
        L_0x0066:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r5.zzexz     // Catch:{ all -> 0x0070 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x0070 }
            r2.zzb(r1)     // Catch:{ all -> 0x0070 }
            throw r0     // Catch:{ all -> 0x0070 }
        L_0x0070:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0070 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzaw.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

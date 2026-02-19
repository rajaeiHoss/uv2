package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;

final class zzbh extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbh(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r3.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004c, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r4) {
        /*
            r3 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r4 = r3.zzexz
            java.lang.Object r4 = r4.mLock
            monitor-enter(r4)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r3.zzexz     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x004d }
            com.google.android.gms.common.api.GoogleApiClient r1 = r3.zzeya     // Catch:{ all -> 0x004d }
            r0.zzb(r1)     // Catch:{ all -> 0x004d }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzexz     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.internal.zzbej r1 = r1.zzext     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.internal.zzben r2 = r3.zzezb     // Catch:{ IllegalStateException -> 0x002a }
            r1.zza((com.google.android.gms.internal.zzben) r2)     // Catch:{ IllegalStateException -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzexz     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x004d }
        L_0x0024:
            r1.zzb(r0)     // Catch:{ all -> 0x004d }
            goto L_0x0041
        L_0x0028:
            r1 = move-exception
            goto L_0x0043
        L_0x002a:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0028 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.common.api.Result r1 = r3.zzb(r1)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0028 }
            r3.setResult(r1)     // Catch:{ all -> 0x0028 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r3.zzexz     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x004d }
            goto L_0x0024
        L_0x0041:
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            return
        L_0x0043:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r3.zzexz     // Catch:{ all -> 0x004d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x004d }
            r2.zzb(r0)     // Catch:{ all -> 0x004d }
            throw r1     // Catch:{ all -> 0x004d }
        L_0x004d:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x004d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbh.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

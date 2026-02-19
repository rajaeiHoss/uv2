package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;

final class zzan extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ TextTrackStyle zzeyc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzan(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, TextTrackStyle textTrackStyle) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyc = textTrackStyle;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002a, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r4.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0045, code lost:
        r4.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004e, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r5) {
        /*
            r4 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r5 = r4.zzexz
            java.lang.Object r5 = r5.mLock
            monitor-enter(r5)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r4.zzexz     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x004f }
            com.google.android.gms.common.api.GoogleApiClient r1 = r4.zzeya     // Catch:{ all -> 0x004f }
            r0.zzb(r1)     // Catch:{ all -> 0x004f }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x002c }
            com.google.android.gms.internal.zzbej r1 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x002c }
            com.google.android.gms.internal.zzben r2 = r4.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x002c }
            com.google.android.gms.cast.TextTrackStyle r3 = r4.zzeyc     // Catch:{ zzbel | IllegalStateException -> 0x002c }
            r1.zza((com.google.android.gms.internal.zzben) r2, (com.google.android.gms.cast.TextTrackStyle) r3)     // Catch:{ zzbel | IllegalStateException -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzexz     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x004f }
        L_0x0026:
            r1.zzb(r0)     // Catch:{ all -> 0x004f }
            goto L_0x0043
        L_0x002a:
            r1 = move-exception
            goto L_0x0045
        L_0x002c:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x002a }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x002a }
            com.google.android.gms.common.api.Result r1 = r4.zzb(r1)     // Catch:{ all -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002a }
            r4.setResult(r1)     // Catch:{ all -> 0x002a }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r4.zzexz     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x004f }
            goto L_0x0026
        L_0x0043:
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            return
        L_0x0045:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r4.zzexz     // Catch:{ all -> 0x004f }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x004f }
            r2.zzb(r0)     // Catch:{ all -> 0x004f }
            throw r1     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzan.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

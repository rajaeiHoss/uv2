package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzaq extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ long zzeyg;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ int zzeyj;
    private /* synthetic */ MediaQueueItem zzeyk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaq(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyk = mediaQueueItem;
        this.zzeyj = i;
        this.zzeyg = j;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0038, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r11.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        r11.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005c, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r12 = r11.zzexz
            java.lang.Object r12 = r12.mLock
            monitor-enter(r12)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzexz     // Catch:{ all -> 0x005d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x005d }
            com.google.android.gms.common.api.GoogleApiClient r1 = r11.zzeya     // Catch:{ all -> 0x005d }
            r0.zzb(r1)     // Catch:{ all -> 0x005d }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            com.google.android.gms.internal.zzbej r2 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            com.google.android.gms.internal.zzben r3 = r11.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            r1 = 1
            com.google.android.gms.cast.MediaQueueItem[] r4 = new com.google.android.gms.cast.MediaQueueItem[r1]     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            r1 = 0
            com.google.android.gms.cast.MediaQueueItem r5 = r11.zzeyk     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            r4[r1] = r5     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            int r5 = r11.zzeyj     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            r6 = 0
            r7 = 0
            long r8 = r11.zzeyg     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            org.json.JSONObject r10 = r11.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            r2.zza((com.google.android.gms.internal.zzben) r3, (com.google.android.gms.cast.MediaQueueItem[]) r4, (int) r5, (int) r6, (int) r7, (long) r8, (org.json.JSONObject) r10)     // Catch:{ zzbel | IllegalStateException -> 0x003a }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ all -> 0x005d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x005d }
        L_0x0034:
            r1.zzb(r0)     // Catch:{ all -> 0x005d }
            goto L_0x0051
        L_0x0038:
            r1 = move-exception
            goto L_0x0053
        L_0x003a:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0038 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0038 }
            com.google.android.gms.common.api.Result r1 = r11.zzb(r1)     // Catch:{ all -> 0x0038 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0038 }
            r11.setResult(r1)     // Catch:{ all -> 0x0038 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ all -> 0x005d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x005d }
            goto L_0x0034
        L_0x0051:
            monitor-exit(r12)     // Catch:{ all -> 0x005d }
            return
        L_0x0053:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r11.zzexz     // Catch:{ all -> 0x005d }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x005d }
            r2.zzb(r0)     // Catch:{ all -> 0x005d }
            throw r1     // Catch:{ all -> 0x005d }
        L_0x005d:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x005d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzaq.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

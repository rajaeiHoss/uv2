package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzav extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ JSONObject zzeyh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzav(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r11.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004b, code lost:
        r11.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0054, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r12 = r11.zzexz
            java.lang.Object r12 = r12.mLock
            monitor-enter(r12)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r11.zzexz     // Catch:{ all -> 0x0055 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0055 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r11.zzeya     // Catch:{ all -> 0x0055 }
            r0.zzb(r1)     // Catch:{ all -> 0x0055 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x0032 }
            com.google.android.gms.internal.zzbej r2 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0032 }
            com.google.android.gms.internal.zzben r3 = r11.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0032 }
            r4 = 0
            r5 = -1
            r7 = 0
            r8 = 1
            r9 = 0
            org.json.JSONObject r10 = r11.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0032 }
            r2.zza((com.google.android.gms.internal.zzben) r3, (int) r4, (long) r5, (com.google.android.gms.cast.MediaQueueItem[]) r7, (int) r8, (java.lang.Integer) r9, (org.json.JSONObject) r10)     // Catch:{ zzbel | IllegalStateException -> 0x0032 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ all -> 0x0055 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0055 }
        L_0x002c:
            r1.zzb(r0)     // Catch:{ all -> 0x0055 }
            goto L_0x0049
        L_0x0030:
            r1 = move-exception
            goto L_0x004b
        L_0x0032:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0030 }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.common.api.Result r1 = r11.zzb(r1)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x0030 }
            r11.setResult(r1)     // Catch:{ all -> 0x0030 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r11.zzexz     // Catch:{ all -> 0x0055 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0055 }
            goto L_0x002c
        L_0x0049:
            monitor-exit(r12)     // Catch:{ all -> 0x0055 }
            return
        L_0x004b:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r11.zzexz     // Catch:{ all -> 0x0055 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x0055 }
            r2.zzb(r0)     // Catch:{ all -> 0x0055 }
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0055 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzav.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

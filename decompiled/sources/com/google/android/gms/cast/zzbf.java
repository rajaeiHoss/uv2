package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzbf extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ double zzeyv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbf(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, double d, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyv = d;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r6.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r6.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r7) {
        /*
            r6 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r7 = r6.zzexz
            java.lang.Object r7 = r7.mLock
            monitor-enter(r7)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r6.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0051 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r6.zzeya     // Catch:{ all -> 0x0051 }
            r0.zzb(r1)     // Catch:{ all -> 0x0051 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzexz     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.zzbej r1 = r1.zzext     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.zzben r2 = r6.zzezb     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            double r3 = r6.zzeyv     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            org.json.JSONObject r5 = r6.zzeyh     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            r1.zza((com.google.android.gms.internal.zzben) r2, (double) r3, (org.json.JSONObject) r5)     // Catch:{ zzbel | IllegalArgumentException | IllegalStateException -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0051 }
        L_0x0028:
            r1.zzb(r0)     // Catch:{ all -> 0x0051 }
            goto L_0x0045
        L_0x002c:
            r1 = move-exception
            goto L_0x0047
        L_0x002e:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x002c }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x002c }
            com.google.android.gms.common.api.Result r1 = r6.zzb(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002c }
            r6.setResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r6.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0051 }
            goto L_0x0028
        L_0x0045:
            monitor-exit(r7)     // Catch:{ all -> 0x0051 }
            return
        L_0x0047:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r6.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x0051 }
            r2.zzb(r0)     // Catch:{ all -> 0x0051 }
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0051 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbf.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

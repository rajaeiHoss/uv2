package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzbg extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ boolean zzeyw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbg(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, boolean z, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyw = z;
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
        r1 = r5.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0047, code lost:
        r5.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0050, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r6) {
        /*
            r5 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r6 = r5.zzexz
            java.lang.Object r6 = r6.mLock
            monitor-enter(r6)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r5.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0051 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r5.zzeya     // Catch:{ all -> 0x0051 }
            r0.zzb(r1)     // Catch:{ all -> 0x0051 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.zzbej r1 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            com.google.android.gms.internal.zzben r2 = r5.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            boolean r3 = r5.zzeyw     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            org.json.JSONObject r4 = r5.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            r1.zza((com.google.android.gms.internal.zzben) r2, (boolean) r3, (org.json.JSONObject) r4)     // Catch:{ zzbel | IllegalStateException -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzexz     // Catch:{ all -> 0x0051 }
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
            com.google.android.gms.common.api.Result r1 = r5.zzb(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002c }
            r5.setResult(r1)     // Catch:{ all -> 0x002c }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r5.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0051 }
            goto L_0x0028
        L_0x0045:
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            return
        L_0x0047:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r5.zzexz     // Catch:{ all -> 0x0051 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x0051 }
            r2.zzb(r0)     // Catch:{ all -> 0x0051 }
            throw r1     // Catch:{ all -> 0x0051 }
        L_0x0051:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0051 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbg.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

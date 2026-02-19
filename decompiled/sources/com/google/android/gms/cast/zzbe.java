package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzbdp;
import org.json.JSONObject;

final class zzbe extends RemoteMediaPlayer.zzb {
    private /* synthetic */ RemoteMediaPlayer zzexz;
    private /* synthetic */ GoogleApiClient zzeya;
    private /* synthetic */ JSONObject zzeyh;
    private /* synthetic */ long zzeyt;
    private /* synthetic */ int zzeyu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, GoogleApiClient googleApiClient2, long j, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzexz = remoteMediaPlayer;
        this.zzeya = googleApiClient2;
        this.zzeyt = j;
        this.zzeyu = i;
        this.zzeyh = jSONObject;
    }

    /* access modifiers changed from: protected */

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        setResult((com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) zzb(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1 = r8.zzexz.zzexu;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0049, code lost:
        r8.zzexz.zzexu.zzb((com.google.android.gms.common.api.GoogleApiClient) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0030 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.zzbdp r9) {
        /*
            r8 = this;
            com.google.android.gms.cast.RemoteMediaPlayer r9 = r8.zzexz
            java.lang.Object r9 = r9.mLock
            monitor-enter(r9)
            com.google.android.gms.cast.RemoteMediaPlayer r0 = r8.zzexz     // Catch:{ all -> 0x0053 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r0 = r0.zzexu     // Catch:{ all -> 0x0053 }
            com.google.android.gms.common.api.GoogleApiClient r1 = r8.zzeya     // Catch:{ all -> 0x0053 }
            r0.zzb(r1)     // Catch:{ all -> 0x0053 }
            r0 = 0
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r8.zzexz     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            com.google.android.gms.internal.zzbej r2 = r1.zzext     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            com.google.android.gms.internal.zzben r3 = r8.zzezb     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            long r4 = r8.zzeyt     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            int r6 = r8.zzeyu     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            org.json.JSONObject r7 = r8.zzeyh     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            r2.zza((com.google.android.gms.internal.zzben) r3, (long) r4, (int) r6, (org.json.JSONObject) r7)     // Catch:{ zzbel | IllegalStateException -> 0x0030 }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r8.zzexz     // Catch:{ all -> 0x0053 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0053 }
        L_0x002a:
            r1.zzb(r0)     // Catch:{ all -> 0x0053 }
            goto L_0x0047
        L_0x002e:
            r1 = move-exception
            goto L_0x0049
        L_0x0030:
            com.google.android.gms.common.api.Status r1 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x002e }
            r2 = 2100(0x834, float:2.943E-42)
            r1.<init>(r2)     // Catch:{ all -> 0x002e }
            com.google.android.gms.common.api.Result r1 = r8.zzb(r1)     // Catch:{ all -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer$MediaChannelResult r1 = (com.google.android.gms.cast.RemoteMediaPlayer.MediaChannelResult) r1     // Catch:{ all -> 0x002e }
            r8.setResult(r1)     // Catch:{ all -> 0x002e }
            com.google.android.gms.cast.RemoteMediaPlayer r1 = r8.zzexz     // Catch:{ all -> 0x0053 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r1 = r1.zzexu     // Catch:{ all -> 0x0053 }
            goto L_0x002a
        L_0x0047:
            monitor-exit(r9)     // Catch:{ all -> 0x0053 }
            return
        L_0x0049:
            com.google.android.gms.cast.RemoteMediaPlayer r2 = r8.zzexz     // Catch:{ all -> 0x0053 }
            com.google.android.gms.cast.RemoteMediaPlayer$zza r2 = r2.zzexu     // Catch:{ all -> 0x0053 }
            r2.zzb(r0)     // Catch:{ all -> 0x0053 }
            throw r1     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r0 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x0053 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzbe.zza(com.google.android.gms.internal.zzbdp):void");
    }
}

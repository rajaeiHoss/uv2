package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzg;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

final class zzcdb implements zzf, zzg {
    private final String packageName;
    private zzcdc zzijb;
    private final String zzijc;
    private final LinkedBlockingQueue<zzba> zzijd = new LinkedBlockingQueue<>();
    private final HandlerThread zzije;

    public zzcdb(Context context, String str, String str2) {
        this.packageName = str;
        this.zzijc = str2;
        HandlerThread handlerThread = new HandlerThread("GassClient");
        this.zzije = handlerThread;
        handlerThread.start();
        this.zzijb = new zzcdc(context, handlerThread.getLooper(), this, this);
        this.zzijb.zzals();
    }

    private final zzcdh zzawa() {
        try {
            return this.zzijb.zzawc();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }

    private static zzba zzawb() {
        zzba zzba = new zzba();
        zzba.zzds = Long.valueOf(PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID);
        return zzba;
    }

    private final void zzny() {
        zzcdc zzcdc = this.zzijb;
        if (zzcdc == null) {
            return;
        }
        if (zzcdc.isConnected() || this.zzijb.isConnecting()) {
            this.zzijb.disconnect();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        zzny();
        r3.zzije.quit();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
        r3.zzijd.put(zzawb());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0025 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            com.google.android.gms.internal.zzcdh r4 = r3.zzawa()
            if (r4 == 0) goto L_0x0039
            com.google.android.gms.internal.zzcdd r0 = new com.google.android.gms.internal.zzcdd     // Catch:{ all -> 0x0025 }
            java.lang.String r1 = r3.packageName     // Catch:{ all -> 0x0025 }
            java.lang.String r2 = r3.zzijc     // Catch:{ all -> 0x0025 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.zzcdf r4 = r4.zza(r0)     // Catch:{ all -> 0x0025 }
            com.google.android.gms.internal.zzba r4 = r4.zzawd()     // Catch:{ all -> 0x0025 }
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.zzba> r0 = r3.zzijd     // Catch:{ all -> 0x0025 }
            r0.put(r4)     // Catch:{ all -> 0x0025 }
        L_0x001c:
            r3.zzny()
            android.os.HandlerThread r4 = r3.zzije
            r4.quit()
            return
        L_0x0025:
            java.util.concurrent.LinkedBlockingQueue<com.google.android.gms.internal.zzba> r4 = r3.zzijd     // Catch:{ InterruptedException -> 0x001c, all -> 0x002f }
            com.google.android.gms.internal.zzba r0 = zzawb()     // Catch:{ InterruptedException -> 0x001c, all -> 0x002f }
            r4.put(r0)     // Catch:{ InterruptedException -> 0x001c, all -> 0x002f }
            goto L_0x001c
        L_0x002f:
            r4 = move-exception
            r3.zzny()
            android.os.HandlerThread r0 = r3.zzije
            r0.quit()
            throw r4
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcdb.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            this.zzijd.put(zzawb());
        } catch (InterruptedException unused) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            this.zzijd.put(zzawb());
        } catch (InterruptedException unused) {
        }
    }

    public final zzba zzdy(int i) {
        zzba zzba;
        try {
            zzba = this.zzijd.poll(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            zzba = null;
        }
        return zzba == null ? zzawb() : zzba;
    }
}

package com.google.firebase.auth.internal;

import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzbhf;
import com.google.firebase.FirebaseApp;

public final class zzq {
    /* access modifiers changed from: private */
    public static zzbhf zzehr = new zzbhf("TokenRefresher", "FirebaseAuth:");
    private Handler mHandler = new Handler(this.zzdhm.getLooper());
    private HandlerThread zzdhm;
    private final FirebaseApp zzmpb;
    volatile long zzmtx;
    volatile long zzmty;
    private long zzmtz;
    private Runnable zzy;

    public zzq(FirebaseApp firebaseApp) {
        zzehr.zza("Initializing TokenRefresher", new Object[0]);
        FirebaseApp firebaseApp2 = (FirebaseApp) zzbq.checkNotNull(firebaseApp);
        this.zzmpb = firebaseApp2;
        HandlerThread handlerThread = new HandlerThread("TokenRefresher", 10);
        this.zzdhm = handlerThread;
        handlerThread.start();
        this.zzy = new zzr(this, firebaseApp2.getName());
        this.zzmtz = 300000;
    }

    public final void cancel() {
        this.mHandler.removeCallbacks(this.zzy);
    }

    public final void zzbun() {
        zzbhf zzbhf = zzehr;
        long j = this.zzmtx - this.zzmtz;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j);
        zzbhf.zza(sb.toString(), new Object[0]);
        cancel();
        this.zzmty = Math.max((this.zzmtx - zzi.zzanq().currentTimeMillis()) - this.zzmtz, 0) / 1000;
        this.mHandler.postDelayed(this.zzy, this.zzmty * 1000);
    }

    /* access modifiers changed from: package-private */
    public final void zzbuo() {
        int i = (int) this.zzmty;
        this.zzmty = (i == 30 || i == 60 || i == 120 || i == 240 || i == 480) ? 2 * this.zzmty : i != 960 ? 30 : 960;
        this.zzmtx = zzi.zzanq().currentTimeMillis() + (this.zzmty * 1000);
        zzbhf zzbhf = zzehr;
        long j = this.zzmtx;
        StringBuilder sb = new StringBuilder(43);
        sb.append("Scheduling refresh for ");
        sb.append(j);
        zzbhf.zza(sb.toString(), new Object[0]);
        this.mHandler.postDelayed(this.zzy, this.zzmty * 1000);
    }
}

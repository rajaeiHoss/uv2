package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import com.google.android.gms.internal.zzaof;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

final class zzau implements zzt<zzaof> {
    private /* synthetic */ CountDownLatch zzanq;

    zzau(CountDownLatch countDownLatch) {
        this.zzanq = countDownLatch;
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        this.zzanq.countDown();
        zzaof.getView().setVisibility(0);
    }
}

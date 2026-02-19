package com.google.android.gms.internal;

import com.google.firebase.internal.zzc;

final class zzede implements Runnable {
    private /* synthetic */ zzc zzmpo;
    private /* synthetic */ zzedd zzmxs;

    zzede(zzedd zzedd, zzc zzc) {
        this.zzmxs = zzedd;
        this.zzmpo = zzc;
    }

    public final void run() {
        this.zzmxs.zzmxq.zzqa(this.zzmpo.getToken());
    }
}

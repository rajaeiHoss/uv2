package com.google.android.gms.internal;

import android.content.Context;

final class zzna implements Runnable {
    private /* synthetic */ Context val$context;
    private /* synthetic */ zzmz zzbkg;

    zzna(zzmz zzmz, Context context) {
        this.zzbkg = zzmz;
        this.val$context = context;
    }

    public final void run() {
        this.zzbkg.getRewardedVideoAdInstance(this.val$context);
    }
}

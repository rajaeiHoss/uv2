package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zzf extends AnimatorListenerAdapter {
    private /* synthetic */ zza zzfcp;
    private /* synthetic */ Runnable zzfcu;

    zzf(zza zza, Runnable runnable) {
        this.zzfcp = zza;
        this.zzfcu = runnable;
    }

    public final void onAnimationEnd(Animator animator) {
        this.zzfcp.setVisibility(8);
        Animator unused = this.zzfcp.zzfcj = null;
        Runnable runnable = this.zzfcu;
        if (runnable != null) {
            runnable.run();
        }
    }
}

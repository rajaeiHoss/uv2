package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zze extends AnimatorListenerAdapter {
    private /* synthetic */ zza zzfcp;

    zze(zza zza) {
        this.zzfcp = zza;
    }

    public final void onAnimationEnd(Animator animator) {
        zza zza = this.zzfcp;
        Animator unused = zza.zzfcj = zza.zzafe();
        this.zzfcp.zzfcj.start();
    }
}

package com.google.android.gms.internal;

import android.animation.Animator;

public final class zzdnp extends zzdno {
    protected final Animator animator;
    /* access modifiers changed from: private */
    public final Runnable zzlxs;
    private final int zzlxt;
    private int zzlxu;
    private zzdnt zzlxv = new zzdnq(this);

    private zzdnp(Animator animator2, int i, Runnable runnable) {
        this.animator = animator2;
        this.zzlxt = -1;
        this.zzlxs = null;
    }

    static /* synthetic */ int zza(zzdnp zzdnp) {
        int i = zzdnp.zzlxu;
        zzdnp.zzlxu = i + 1;
        return i;
    }

    public static void zza(Animator animator2, int i, Runnable runnable) {
        animator2.addListener(new zzdnp(animator2, -1, (Runnable) null));
    }

    /* access modifiers changed from: private */
    public final boolean zzbme() {
        int i = this.zzlxt;
        return i != -1 && this.zzlxu >= i;
    }

    public final void onAnimationEnd(Animator animator2) {
        if (!zzb(animator2)) {
            zzdnr.zzbmf().zza(this.zzlxv);
        }
    }
}

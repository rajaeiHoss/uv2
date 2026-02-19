package com.google.android.gms.internal;

import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

final class zzaal implements ViewTreeObserver.OnGlobalLayoutListener {
    private /* synthetic */ zzaaf zzcqd;
    private /* synthetic */ WeakReference zzcqe;

    zzaal(zzaaf zzaaf, WeakReference weakReference) {
        this.zzcqd = zzaaf;
        this.zzcqe = weakReference;
    }

    public final void onGlobalLayout() {
        this.zzcqd.zza((WeakReference<zzaof>) this.zzcqe, false);
    }
}

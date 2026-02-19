package com.google.android.gms.internal;

import android.app.Activity;

final class zzfba {
    private final Activity mActivity;
    private final Object zzoxj;
    private final Runnable zzy;

    public zzfba(Activity activity, Runnable runnable, Object obj) {
        this.mActivity = activity;
        this.zzy = runnable;
        this.zzoxj = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzfba)) {
            return false;
        }
        zzfba zzfba = (zzfba) obj;
        return zzfba.zzoxj.equals(this.zzoxj) && zzfba.zzy == this.zzy && zzfba.mActivity == this.mActivity;
    }

    public final Activity getActivity() {
        return this.mActivity;
    }

    public final int hashCode() {
        return this.zzoxj.hashCode();
    }

    public final Runnable zzbmh() {
        return this.zzy;
    }

    public final Object zzcol() {
        return this.zzoxj;
    }
}

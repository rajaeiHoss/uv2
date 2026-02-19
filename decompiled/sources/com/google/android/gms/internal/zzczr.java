package com.google.android.gms.internal;

import android.util.Log;

public final class zzczr implements zzdam {
    private int zzebd = 5;
    private boolean zzkwd = true;

    private final boolean zzae(int i) {
        if (!this.zzkwd || !Log.isLoggable("GoogleTagManager", i)) {
            return !this.zzkwd && this.zzebd <= i;
        }
        return true;
    }

    public final void e(String str) {
        if (zzae(6)) {
            Log.e("GoogleTagManager", str);
        }
    }

    public final void v(String str) {
        if (zzae(2)) {
            Log.v("GoogleTagManager", str);
        }
    }

    public final void zzb(String str, Throwable th) {
        if (zzae(6)) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public final void zzc(String str, Throwable th) {
        if (zzae(5)) {
            Log.w("GoogleTagManager", str, th);
        }
    }

    public final void zzcy(String str) {
        if (zzae(4)) {
            Log.i("GoogleTagManager", str);
        }
    }

    public final void zzcz(String str) {
        if (zzae(5)) {
            Log.w("GoogleTagManager", str);
        }
    }
}

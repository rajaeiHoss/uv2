package com.google.android.gms.internal;

import android.os.Build;
import android.os.Looper;

final class zzdns extends ThreadLocal<zzdnr> {
    zzdns() {
    }

    /* access modifiers changed from: protected */
    public final zzdnr initialValue() {
        if (Build.VERSION.SDK_INT >= 16) {
            return new zzdnx();
        }
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            return new zzdnw(myLooper);
        }
        throw new IllegalStateException("The current thread must have a looper!");
    }
}

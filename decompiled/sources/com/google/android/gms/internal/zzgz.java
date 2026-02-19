package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

final class zzgz implements zzhe {
    private /* synthetic */ Activity val$activity;

    zzgz(zzgw zzgw, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityResumed(this.val$activity);
    }
}

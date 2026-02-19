package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

final class zzhb implements zzhe {
    private /* synthetic */ Activity val$activity;

    zzhb(zzgw zzgw, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStopped(this.val$activity);
    }
}

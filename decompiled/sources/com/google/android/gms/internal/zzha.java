package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

final class zzha implements zzhe {
    private /* synthetic */ Activity val$activity;

    zzha(zzgw zzgw, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityPaused(this.val$activity);
    }
}

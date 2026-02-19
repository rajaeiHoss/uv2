package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

final class zzdf implements zzdi {
    private /* synthetic */ Activity val$activity;

    zzdf(zzda zzda, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityStopped(this.val$activity);
    }
}

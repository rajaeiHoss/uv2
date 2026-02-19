package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;

final class zzde implements zzdi {
    private /* synthetic */ Activity val$activity;

    zzde(zzda zzda, Activity activity) {
        this.val$activity = activity;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityPaused(this.val$activity);
    }
}

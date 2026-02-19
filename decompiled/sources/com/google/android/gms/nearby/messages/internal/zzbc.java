package com.google.android.gms.nearby.messages.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

final class zzbc implements Application.ActivityLifecycleCallbacks {
    private final Activity zzkdd;
    private final zzak zzkdt;

    private zzbc(Activity activity, zzak zzak) {
        this.zzkdd = activity;
        this.zzkdt = zzak;
    }

    /* synthetic */ zzbc(Activity activity, zzak zzak, zzau zzau) {
        this(activity, zzak);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (activity == this.zzkdd) {
            Log.v("NearbyMessages", String.format("Unregistering ClientLifecycleSafetyNet's ActivityLifecycleCallbacks for %s", new Object[]{activity.getPackageName()}));
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
        if (activity == this.zzkdd) {
            this.zzkdt.zzet(1);
        }
    }
}

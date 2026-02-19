package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class zzdb implements zzdi {
    private /* synthetic */ Activity val$activity;
    private /* synthetic */ Bundle zzaik;

    zzdb(zzda zzda, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzaik = bundle;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivityCreated(this.val$activity, this.zzaik);
    }
}

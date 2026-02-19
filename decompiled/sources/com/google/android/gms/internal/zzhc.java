package com.google.android.gms.internal;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

final class zzhc implements zzhe {
    private /* synthetic */ Activity val$activity;
    private /* synthetic */ Bundle zzaik;

    zzhc(zzgw zzgw, Activity activity, Bundle bundle) {
        this.val$activity = activity;
        this.zzaik = bundle;
    }

    public final void zza(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        activityLifecycleCallbacks.onActivitySaveInstanceState(this.val$activity, this.zzaik);
    }
}

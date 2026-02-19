package com.google.firebase.remoteconfig;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.internal.zzbio;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zza implements ResultCallback<zzbio> {
    private /* synthetic */ TaskCompletionSource zzosg;
    private /* synthetic */ FirebaseRemoteConfig zzosh;

    zza(FirebaseRemoteConfig firebaseRemoteConfig, TaskCompletionSource taskCompletionSource) {
        this.zzosh = firebaseRemoteConfig;
        this.zzosg = taskCompletionSource;
    }

    public final void onResult(zzbio result) {
        this.zzosh.zza((TaskCompletionSource<Void>) this.zzosg, result);
    }
}

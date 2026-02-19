package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

final class zzbxn extends Api.zza<zzbxj, FitnessOptions> {
    zzbxn() {
    }

    public final zzbxj zza(Context context, Looper looper, zzr zzr, FitnessOptions fitnessOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbxj(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

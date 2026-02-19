package com.google.android.gms.safetynet;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcxs;

final class zzk extends Api.zza<zzcxs, Api.ApiOptions.NoOptions> {
    zzk() {
    }

    public final zzcxs zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcxs(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

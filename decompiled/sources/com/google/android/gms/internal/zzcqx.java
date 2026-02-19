package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzcqx extends Api.zza<zzcov, Api.ApiOptions.NoOptions> {
    zzcqx() {
    }

    public final zzcov zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcov(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

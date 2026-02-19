package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzbxx extends Api.zza<zzbxv, Api.ApiOptions.NoOptions> {
    zzbxx() {
    }

    public final zzbxv zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbxv(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

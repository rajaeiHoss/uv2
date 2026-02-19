package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzbdk extends Api.zza<zzbdn, Api.ApiOptions.NoOptions> {
    zzbdk() {
    }

    public final zzbdn zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbdn(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

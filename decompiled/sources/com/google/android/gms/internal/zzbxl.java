package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzbxl extends Api.zza<zzbxj, Api.ApiOptions.NoOptions> {
    zzbxl() {
    }

    public final zzbxj zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbxj(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

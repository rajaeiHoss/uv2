package com.google.android.gms.panorama;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcvu;

final class zzb extends Api.zza<zzcvu, Api.ApiOptions.NoOptions> {
    zzb() {
    }

    public final zzcvu zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcvu(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

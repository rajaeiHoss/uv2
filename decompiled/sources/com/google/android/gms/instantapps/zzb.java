package com.google.android.gms.instantapps;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcet;

final class zzb extends Api.zza<zzcet, Api.ApiOptions.NoOptions> {
    zzb() {
    }

    public final zzcet zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcet(context, looper, connectionCallbacks, onConnectionFailedListener);
    }
}

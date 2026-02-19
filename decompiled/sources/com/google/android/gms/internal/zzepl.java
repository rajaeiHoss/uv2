package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzepl extends Api.zza<zzepm, Api.ApiOptions.NoOptions> {
    zzepl() {
    }

    public final zzepm zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzepm(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

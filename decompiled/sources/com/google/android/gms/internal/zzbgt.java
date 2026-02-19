package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzbgt extends Api.zza<zzbha, Api.ApiOptions.NoOptions> {
    zzbgt() {
    }

    public final zzbha zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbha(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

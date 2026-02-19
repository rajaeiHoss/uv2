package com.google.android.gms.search;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcxy;

final class zzb extends Api.zza<zzcxy, Api.ApiOptions.NoOptions> {
    zzb() {
    }

    public final zzcxy zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcxy(context, connectionCallbacks, onConnectionFailedListener, zzr);
    }
}

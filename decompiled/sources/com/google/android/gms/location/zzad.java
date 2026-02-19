package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzchh;

final class zzad extends Api.zza<zzchh, Api.ApiOptions.NoOptions> {
    zzad() {
    }

    public final zzchh zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzchh(context, looper, connectionCallbacks, onConnectionFailedListener, "locationServices", zzr);
    }
}

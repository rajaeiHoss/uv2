package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzcok extends Api.zza<zzcoi, Api.ApiOptions.NoOptions> {
    zzcok() {
    }

    public final zzcoi zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcoi(context, looper, connectionCallbacks, onConnectionFailedListener, zzr);
    }
}

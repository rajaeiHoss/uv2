package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzawd;

final class zzb extends Api.zza<zzawd, Api.ApiOptions.NoOptions> {
    zzb() {
    }

    public final zzawd zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzawd(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

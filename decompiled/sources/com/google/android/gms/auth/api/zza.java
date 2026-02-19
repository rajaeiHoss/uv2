package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzaxi;

final class zza extends Api.zza<zzaxi, Auth.AuthCredentialsOptions> {
    zza() {
    }

    public final zzaxi zza(Context context, Looper looper, zzr zzr, Auth.AuthCredentialsOptions authCredentialsOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaxi(context, looper, zzr, authCredentialsOptions, connectionCallbacks, onConnectionFailedListener);
    }
}

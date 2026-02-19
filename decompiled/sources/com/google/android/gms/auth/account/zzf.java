package com.google.android.gms.auth.account;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzawa;

final class zzf extends Api.zza<zzawa, Api.ApiOptions.NoOptions> {
    zzf() {
    }

    public final zzawa zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzawa(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

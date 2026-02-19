package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

public final class zzbwb extends Api.zza<zzbwa, Api.ApiOptions.NoOptions> {
    public final zzbwa zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbwa(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

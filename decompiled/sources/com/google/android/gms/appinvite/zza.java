package com.google.android.gms.appinvite;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzavk;

final class zza extends Api.zza<zzavk, Api.ApiOptions.NoOptions> {
    zza() {
    }

    public final zzavk zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzavk(context, looper, connectionCallbacks, onConnectionFailedListener, zzr);
    }
}

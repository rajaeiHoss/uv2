package com.google.android.gms.phenotype;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcvz;

final class zzl extends Api.zza<zzcvz, Api.ApiOptions.NoOptions> {
    zzl() {
    }

    public final zzcvz zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzcvz(context, looper, zzr, connectionCallbacks, onConnectionFailedListener);
    }
}

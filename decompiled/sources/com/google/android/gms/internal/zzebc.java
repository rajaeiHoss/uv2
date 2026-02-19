package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzebc extends Api.zza<zzeau, zzebd> {
    zzebc() {
    }

    public final zzeau zza(Context context, Looper looper, zzr zzr, zzebd zzebd, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzeav(context, looper, zzr, zzebd, connectionCallbacks, onConnectionFailedListener);
    }
}

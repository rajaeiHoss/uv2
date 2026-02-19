package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzaxs;

final class zze extends Api.zza<zzaxs, zzf> {
    zze() {
    }

    public final zzaxs zza(Context context, Looper looper, zzr zzr, zzf zzf, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzaxs(context, looper, zzr, zzf, connectionCallbacks, onConnectionFailedListener);
    }
}

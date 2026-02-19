package com.google.android.gms.awareness;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzblg;

final class zza extends Api.zza<zzblg, AwarenessOptions> {
    zza() {
    }

    public final zzblg zza(Context context, Looper looper, zzr zzr, AwarenessOptions awarenessOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzblg(context, looper, zzr, awarenessOptions, connectionCallbacks, onConnectionFailedListener);
    }
}

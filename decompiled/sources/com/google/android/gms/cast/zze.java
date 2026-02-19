package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbdp;

final class zze extends Api.zza<zzbdp, Cast.CastOptions> {
    zze() {
    }

    public final zzbdp zza(Context context, Looper looper, zzr zzr, Cast.CastOptions castOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.checkNotNull(castOptions, "Setting the API options is required.");
        return new zzbdp(context, looper, zzr, castOptions.zzetj, (long) castOptions.zzetl, castOptions.zzetk, castOptions.extras, connectionCallbacks, onConnectionFailedListener);
    }
}

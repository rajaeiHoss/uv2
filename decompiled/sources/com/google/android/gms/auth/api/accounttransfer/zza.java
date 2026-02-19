package com.google.android.gms.auth.api.accounttransfer;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzawi;

final class zza extends Api.zza<zzawi, zzn> {
    zza() {
    }

    public final zzawi zza(Context context, Looper looper, zzr zzr, zzn zzn, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzawi(context, looper, zzr, zzn, connectionCallbacks, onConnectionFailedListener);
    }
}

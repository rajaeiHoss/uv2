package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbnq;

final class zzf extends Api.zza<zzbnq, Api.ApiOptions.NoOptions> {
    zzf() {
    }

    public final zzbnq zza(Context context, Looper looper, zzr zzr, Api.ApiOptions.NoOptions noOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbnq(context, looper, zzr, connectionCallbacks, onConnectionFailedListener, new Bundle());
    }
}

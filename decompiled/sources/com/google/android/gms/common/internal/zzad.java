package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzad implements zzg {
    private /* synthetic */ GoogleApiClient.OnConnectionFailedListener zzggk;

    zzad(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.zzggk = onConnectionFailedListener;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzggk.onConnectionFailed(connectionResult);
    }
}

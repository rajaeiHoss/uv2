package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzbd implements GoogleApiClient.OnConnectionFailedListener {
    private /* synthetic */ zzdb zzfyt;

    zzbd(zzba zzba, zzdb zzdb) {
        this.zzfyt = zzdb;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfyt.setResult(new Status(8));
    }
}

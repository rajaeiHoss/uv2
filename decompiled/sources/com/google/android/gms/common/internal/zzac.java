package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzac implements zzf {
    private /* synthetic */ GoogleApiClient.ConnectionCallbacks zzggj;

    zzac(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        this.zzggj = connectionCallbacks;
    }

    public final void onConnected(Bundle bundle) {
        this.zzggj.onConnected(bundle);
    }

    public final void onConnectionSuspended(int i) {
        this.zzggj.onConnectionSuspended(i);
    }
}

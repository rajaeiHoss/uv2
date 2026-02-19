package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzax implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private /* synthetic */ zzao zzfxt;

    private zzax(zzao zzao) {
        this.zzfxt = zzao;
    }

    /* synthetic */ zzax(zzao zzao, zzap zzap) {
        this(zzao);
    }

    public final void onConnected(Bundle bundle) {
        this.zzfxt.zzfxl.zza(new zzav(this.zzfxt));
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfxt.zzfwa.lock();
        try {
            if (this.zzfxt.zzd(connectionResult)) {
                this.zzfxt.zzajn();
                this.zzfxt.zzajl();
            } else {
                this.zzfxt.zze(connectionResult);
            }
        } finally {
            this.zzfxt.zzfwa.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}

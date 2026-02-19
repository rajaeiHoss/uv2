package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

final class zzcye extends zzcya {
    private /* synthetic */ zzcyd zzkli;

    zzcye(zzcyd zzcyd) {
        this.zzkli = zzcyd;
    }

    public final void zza(Status status, GoogleNowAuthState googleNowAuthState) {
        if (this.zzkli.zzklf) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl success");
        }
        this.zzkli.setResult(new zzcyf(status, googleNowAuthState));
    }
}

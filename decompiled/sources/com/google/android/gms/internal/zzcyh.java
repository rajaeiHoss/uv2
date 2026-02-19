package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;

final class zzcyh extends Api.zza<zzcyt, zzcyk> {
    zzcyh() {
    }

    public final zzcyt zza(Context context, Looper looper, zzr zzr, zzcyk zzcyk, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (zzcyk == null) {
            zzcyk = zzcyk.zzklp;
        }
        return new zzcyt(context, looper, true, zzr, zzcyk, connectionCallbacks, onConnectionFailedListener);
    }
}

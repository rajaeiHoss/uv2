package com.google.android.gms.drive;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.internal.zzbnq;

final class zzg extends Api.zza<zzbnq, Drive.zzb> {
    zzg() {
    }

    public final zzbnq zza(Context context, Looper looper, zzr zzr, Drive.zzb zzb, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (zzb == null) {
            return new zzbnq(context, looper, zzr, connectionCallbacks, onConnectionFailedListener, new Bundle());
        }
        throw new NoSuchMethodError();
    }
}

package com.google.android.gms.drive;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.internal.zzbnq;

final class zzh extends Api.zza<zzbnq, Drive.zza> {
    zzh() {
    }

    public final zzbnq zza(Context context, Looper looper, zzr zzr, Drive.zza zza, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zzbnq(context, looper, zzr, connectionCallbacks, onConnectionFailedListener, zza.zzapj());
    }
}

package com.google.android.gms.wearable;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.internal.zzhg;

final class zzj extends Api.zza<zzhg, Wearable.WearableOptions> {
    zzj() {
    }

    public final zzhg zza(Context context, Looper looper, zzr zzr, Wearable.WearableOptions wearableOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (wearableOptions == null) {
            wearableOptions = new Wearable.WearableOptions(new Wearable.WearableOptions.Builder(), (zzj) null);
        }
        return new zzhg(context, looper, connectionCallbacks, onConnectionFailedListener, zzr);
    }
}

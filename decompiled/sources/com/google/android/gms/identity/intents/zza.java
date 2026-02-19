package com.google.android.gms.identity.intents;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.internal.zzcdo;

final class zza extends Api.zza<zzcdo, Address.AddressOptions> {
    zza() {
    }

    public final zzcdo zza(Context context, Looper looper, zzr zzr, Address.AddressOptions addressOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzbq.checkArgument(context instanceof Activity, "An Activity must be used for Address APIs");
        if (addressOptions == null) {
            addressOptions = new Address.AddressOptions();
        }
        return new zzcdo((Activity) context, looper, zzr, addressOptions.theme, connectionCallbacks, onConnectionFailedListener);
    }
}

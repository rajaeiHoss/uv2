package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.location.places.PlacesOptions;

public final class zzq extends Api.zza<zzo, PlacesOptions> {
    public final zzo zza(Context context, Looper looper, zzr zzr, PlacesOptions placesOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (placesOptions == null) {
            placesOptions = new PlacesOptions.Builder().build();
        }
        return new zzo(context, looper, zzr, connectionCallbacks, onConnectionFailedListener, context.getPackageName(), placesOptions);
    }
}

package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbfa;

final class zzo extends Api.zza<zzbfa, CastRemoteDisplay.CastRemoteDisplayOptions> {
    zzo() {
    }

    public final zzbfa zza(Context context, Looper looper, zzr zzr, CastRemoteDisplay.CastRemoteDisplayOptions castRemoteDisplayOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", castRemoteDisplayOptions.zzeuf);
        return new zzbfa(context, looper, zzr, castRemoteDisplayOptions.zzetj, bundle, castRemoteDisplayOptions.zzeue, connectionCallbacks, onConnectionFailedListener);
    }
}

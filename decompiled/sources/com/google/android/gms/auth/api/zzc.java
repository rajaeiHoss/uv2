package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import java.util.Collections;
import java.util.List;

final class zzc extends Api.zza<zze, GoogleSignInOptions> {
    zzc() {
    }

    public final zze zza(Context context, Looper looper, zzr zzr, GoogleSignInOptions googleSignInOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        return new zze(context, looper, zzr, googleSignInOptions, connectionCallbacks, onConnectionFailedListener);
    }

    public final List zzr(GoogleSignInOptions googleSignInOptions) {
        return googleSignInOptions == null ? Collections.emptyList() : googleSignInOptions.zzaci();
    }
}

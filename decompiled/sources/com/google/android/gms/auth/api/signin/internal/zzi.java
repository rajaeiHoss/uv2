package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzi extends zzm<Status> {
    zzi(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zze zze) throws RemoteException {
        ((zzv) zze.zzalw()).zzb(new zzj(this), zze.zzaco());
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

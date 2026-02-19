package com.google.android.gms.auth.api.signin.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzk extends zzm<Status> {
    zzk(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zze zze) throws RemoteException {
        ((zzv) zze.zzalw()).zzc(new zzl(this), zze.zzaco());
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

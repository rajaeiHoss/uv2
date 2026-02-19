package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.instantapps.zzd;

final class zzcem extends zzcer<zzd> {
    zzcem(zzcej zzcej, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzcef zzcef) throws RemoteException {
        zzcef.zza(new zzceo(this));
    }

    /* access modifiers changed from: protected */
    public final zzd zzb(Status status) {
        return new zzcen(this, status);
    }
}

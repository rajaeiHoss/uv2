package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzayg extends zzm<ProxyApi.ProxyResult, zzaxs> {
    public zzayg(GoogleApiClient googleApiClient) {
        super((Api<?>) zzd.API, googleApiClient);
    }

    /* access modifiers changed from: protected */
    public abstract void zza(Context context, zzaxv zzaxv) throws RemoteException;

    /* access modifiers changed from: protected */
    public final void zza(zzaxs zzaxs) throws RemoteException {
        zza(zzaxs.getContext(), (zzaxv) zzaxs.zzalw());
    }

    /* access modifiers changed from: protected */
    public final ProxyApi.ProxyResult zzb(Status status) {
        return new zzayk(status);
    }
}

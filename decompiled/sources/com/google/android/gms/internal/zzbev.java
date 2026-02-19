package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

class zzbev extends zzm<CastRemoteDisplay.CastRemoteDisplaySessionResult, zzbfa> {
    final /* synthetic */ zzbeq zzfor;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbev(zzbeq zzbeq, GoogleApiClient googleApiClient) {
        super((Api<?>) zzbeq.zzfop, googleApiClient);
        this.zzfor = zzbeq;
    }

    public void zza(zzbfa zzbfa) throws RemoteException {
    }

    /* access modifiers changed from: protected */
    public final CastRemoteDisplay.CastRemoteDisplaySessionResult zzb(Status status) {
        return new zzbey(status);
    }
}

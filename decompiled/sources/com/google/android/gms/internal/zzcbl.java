package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.fitness.result.SessionReadResult;

final class zzcbl extends zzbye<SessionReadResult> {
    private /* synthetic */ SessionReadRequest zzhnk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbl(zzcbh zzcbh, GoogleApiClient googleApiClient, SessionReadRequest sessionReadRequest) {
        super(googleApiClient);
        this.zzhnk = sessionReadRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new SessionReadRequest(this.zzhnk, (zzbzn) new zzcbo(this, (zzcbi) null)));
    }

    /* access modifiers changed from: protected */
    public final SessionReadResult zzb(Status status) {
        return SessionReadResult.zzah(status);
    }
}

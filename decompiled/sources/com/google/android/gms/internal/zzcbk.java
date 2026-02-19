package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.SessionInsertRequest;

final class zzcbk extends zzbyg {
    private /* synthetic */ SessionInsertRequest zzhnj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbk(zzcbh zzcbh, GoogleApiClient googleApiClient, SessionInsertRequest sessionInsertRequest) {
        super(googleApiClient);
        this.zzhnj = sessionInsertRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbyb zzbyb) throws RemoteException {
        ((zzbzi) zzbyb.zzalw()).zza(new SessionInsertRequest(this.zzhnj, (zzbzt) new zzcbq(this)));
    }
}

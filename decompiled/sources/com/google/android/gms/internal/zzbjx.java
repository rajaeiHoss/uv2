package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.awareness.fence.FenceQueryRequest;
import com.google.android.gms.awareness.fence.FenceQueryResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;

final class zzbjx extends zzblb {
    private /* synthetic */ FenceQueryRequest zzgna;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbjx(zzbjv zzbjv, GoogleApiClient googleApiClient, FenceQueryRequest fenceQueryRequest) {
        super(googleApiClient);
        this.zzgna = fenceQueryRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzblg zzblg) throws RemoteException {
        zzblg.zza((zzn<FenceQueryResult>) this, (zzbkg) this.zzgna);
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.awareness.fence.FenceUpdateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzbjw extends zzblf {
    private /* synthetic */ FenceUpdateRequest zzgmz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbjw(zzbjv zzbjv, GoogleApiClient googleApiClient, FenceUpdateRequest fenceUpdateRequest) {
        super(googleApiClient);
        this.zzgmz = fenceUpdateRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzblg zzblg) throws RemoteException {
        zzblg.zza((zzn<Status>) this, (zzbkp) this.zzgmz);
    }
}

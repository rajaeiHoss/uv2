package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzauz;

final class zzava extends zzauz.zzc<Status> {
    private /* synthetic */ zzauo[] zzego;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzava(zzauz zzauz, GoogleApiClient googleApiClient, zzauo[] zzauoArr) {
        super(googleApiClient);
        this.zzego = zzauoArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzaus zzaus) throws RemoteException {
        zzaus.zza(new zzauz.zzd(this), (String) null, this.zzego);
    }
}

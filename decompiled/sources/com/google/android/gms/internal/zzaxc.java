package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzaxc extends zzaxg<Status> {
    private /* synthetic */ Credential zzelu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaxc(zzawz zzawz, GoogleApiClient googleApiClient, Credential credential) {
        super(googleApiClient);
        this.zzelu = credential;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzaxn zzaxn) throws RemoteException {
        zzaxn.zza((zzaxl) new zzaxf(this), new zzaxp(this.zzelu));
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

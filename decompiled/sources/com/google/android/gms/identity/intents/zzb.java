package com.google.android.gms.identity.intents;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.internal.zzcdo;

final class zzb extends Address.zza {
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ UserAddressRequest zzilm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzb(GoogleApiClient googleApiClient, UserAddressRequest userAddressRequest, int i) {
        super(googleApiClient);
        this.zzilm = userAddressRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcdo zzcdo) throws RemoteException {
        zzcdo.zza(this.zzilm, this.val$requestCode);
        setResult(Status.zzftq);
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet;

final class zzdmp extends Wallet.zzb {
    private /* synthetic */ int val$requestCode;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdmp(zzdmo zzdmo, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zzfq(this.val$requestCode);
        setResult(Status.zzftq);
    }
}

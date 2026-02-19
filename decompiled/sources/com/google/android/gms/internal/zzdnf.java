package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.CreateWalletObjectsRequest;
import com.google.android.gms.wallet.Wallet;

final class zzdnf extends Wallet.zzb {
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ CreateWalletObjectsRequest zzlog;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdnf(zzdne zzdne, GoogleApiClient googleApiClient, CreateWalletObjectsRequest createWalletObjectsRequest, int i) {
        super(googleApiClient);
        this.zzlog = createWalletObjectsRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zza(this.zzlog, this.val$requestCode);
        setResult(Status.zzftq);
    }
}

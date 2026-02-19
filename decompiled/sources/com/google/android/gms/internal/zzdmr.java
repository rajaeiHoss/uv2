package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.FullWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class zzdmr extends Wallet.zzb {
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ FullWalletRequest zzlpp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdmr(zzdmo zzdmo, GoogleApiClient googleApiClient, FullWalletRequest fullWalletRequest, int i) {
        super(googleApiClient);
        this.zzlpp = fullWalletRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zza(this.zzlpp, this.val$requestCode);
        setResult(Status.zzftq);
    }
}

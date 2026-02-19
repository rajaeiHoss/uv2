package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.Wallet;

final class zzdmq extends Wallet.zzb {
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ MaskedWalletRequest zzlpo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdmq(zzdmo zzdmo, GoogleApiClient googleApiClient, MaskedWalletRequest maskedWalletRequest, int i) {
        super(googleApiClient);
        this.zzlpo = maskedWalletRequest;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zza(this.zzlpo, this.val$requestCode);
        setResult(Status.zzftq);
    }
}

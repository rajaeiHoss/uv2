package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;

final class zzdmt extends Wallet.zza<BooleanResult> {
    zzdmt(zzdmo zzdmo, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zza(IsReadyToPayRequest.newBuilder().build(), (zzn<BooleanResult>) this);
    }

    /* access modifiers changed from: protected */
    public final BooleanResult zzb(Status status) {
        return new BooleanResult(status, false);
    }
}

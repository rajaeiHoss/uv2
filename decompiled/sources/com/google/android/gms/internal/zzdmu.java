package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.Wallet;

final class zzdmu extends Wallet.zza<BooleanResult> {
    private /* synthetic */ IsReadyToPayRequest zzlno;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdmu(zzdmo zzdmo, GoogleApiClient googleApiClient, IsReadyToPayRequest isReadyToPayRequest) {
        super(googleApiClient);
        this.zzlno = isReadyToPayRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zza(this.zzlno, (zzn<BooleanResult>) this);
    }

    /* access modifiers changed from: protected */
    public final BooleanResult zzb(Status status) {
        return new BooleanResult(status, false);
    }
}

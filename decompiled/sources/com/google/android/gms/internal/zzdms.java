package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.Wallet;

final class zzdms extends Wallet.zzb {
    private /* synthetic */ int val$requestCode;
    private /* synthetic */ String zzlpq;
    private /* synthetic */ String zzlpr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdms(zzdmo zzdmo, GoogleApiClient googleApiClient, String str, String str2, int i) {
        super(googleApiClient);
        this.zzlpq = str;
        this.zzlpr = str2;
        this.val$requestCode = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv) {
        zzdmv.zzc(this.zzlpq, this.zzlpr, this.val$requestCode);
        setResult(Status.zzftq);
    }
}

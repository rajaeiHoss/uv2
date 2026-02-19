package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zzd;
import com.google.android.gms.location.places.zze;

final class zzk extends zze<zzo> {
    private /* synthetic */ String zzixc;
    private /* synthetic */ int zzixd;
    private /* synthetic */ int zzixe;
    private /* synthetic */ int zzixf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzk(zzh zzh, Api api, GoogleApiClient googleApiClient, String str, int i, int i2, int i3) {
        super(api, googleApiClient);
        this.zzixc = str;
        this.zzixd = i;
        this.zzixe = i2;
        this.zzixf = i3;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzo zzo) throws RemoteException {
        zzo.zza(new zzd((zze) this), this.zzixc, this.zzixd, this.zzixe, this.zzixf);
    }
}

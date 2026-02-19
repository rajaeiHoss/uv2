package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.request.zzs;
import com.google.android.gms.fitness.result.DataTypeResult;

final class zzcag extends zzbxb<DataTypeResult> {
    private /* synthetic */ String zzhmm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcag(zzcae zzcae, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzhmm = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbwy zzbwy) throws RemoteException {
        ((zzbyy) zzbwy.zzalw()).zza(new zzs(this.zzhmm, (zzbyq) new zzcai(this, (zzcaf) null)));
    }

    /* access modifiers changed from: protected */
    public final DataTypeResult zzb(Status status) {
        return DataTypeResult.zzaf(status);
    }
}

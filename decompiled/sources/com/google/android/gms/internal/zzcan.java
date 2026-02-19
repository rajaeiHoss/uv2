package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.zzk;

final class zzcan extends zzbxo {
    private /* synthetic */ DataSet zzhmp;
    private /* synthetic */ boolean zzhmq = false;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcan(zzcam zzcam, GoogleApiClient googleApiClient, DataSet dataSet, boolean z) {
        super(googleApiClient);
        this.zzhmp = dataSet;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new zzk(this.zzhmp, (zzbzt) new zzcbq(this), this.zzhmq));
    }
}

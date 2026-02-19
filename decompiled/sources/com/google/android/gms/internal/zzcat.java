package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzg;
import com.google.android.gms.fitness.result.DailyTotalResult;

final class zzcat extends zzbxm<DailyTotalResult> {
    private /* synthetic */ DataType zzhmw;
    private /* synthetic */ boolean zzhmx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcat(zzcam zzcam, GoogleApiClient googleApiClient, DataType dataType, boolean z) {
        super(googleApiClient);
        this.zzhmw = dataType;
        this.zzhmx = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxj zzbxj) throws RemoteException {
        ((zzbzc) zzbxj.zzalw()).zza(new zzg((zzbyh) new zzcau(this), this.zzhmw, this.zzhmx));
    }

    /* access modifiers changed from: protected */
    public final DailyTotalResult zzb(Status status) {
        return DailyTotalResult.zza(status, this.zzhmw);
    }
}

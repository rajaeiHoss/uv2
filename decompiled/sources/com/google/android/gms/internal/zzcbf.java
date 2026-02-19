package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzao;

final class zzcbf extends zzbya {
    private /* synthetic */ SensorRequest zzhhr;
    private /* synthetic */ zzt zzhne;
    private /* synthetic */ PendingIntent zzhnf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbf(zzcbd zzcbd, GoogleApiClient googleApiClient, SensorRequest sensorRequest, zzt zzt, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhhr = sensorRequest;
        this.zzhne = zzt;
        this.zzhnf = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxv zzbxv) throws RemoteException {
        ((zzbzg) zzbxv.zzalw()).zza(new zzao(this.zzhhr, this.zzhne, this.zzhnf, new zzcbq(this)));
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

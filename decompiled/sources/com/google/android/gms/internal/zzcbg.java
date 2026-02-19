package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.zzar;

final class zzcbg extends zzbya {
    private /* synthetic */ PendingIntent zzhmu;
    private /* synthetic */ zzt zzhng;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbg(zzcbd zzcbd, GoogleApiClient googleApiClient, zzt zzt, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzhng = zzt;
        this.zzhmu = pendingIntent;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxv zzbxv) throws RemoteException {
        ((zzbzg) zzbxv.zzalw()).zza(new zzar(this.zzhng, this.zzhmu, (zzbzt) new zzcbq(this)));
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

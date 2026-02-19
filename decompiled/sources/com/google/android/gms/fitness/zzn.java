package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzao;
import com.google.android.gms.internal.zzbxv;
import com.google.android.gms.internal.zzbzg;
import com.google.android.gms.internal.zzcbq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends zzcq<zzbxv, OnDataPointListener> {
    private /* synthetic */ zzci zzgvn;
    private /* synthetic */ SensorRequest zzhhr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(SensorsClient sensorsClient, zzci zzci, zzci zzci2, SensorRequest sensorRequest) {
        super(zzci);
        this.zzgvn = zzci2;
        this.zzhhr = sensorRequest;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzbxv zzbxv, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbzg) zzbxv.zzalw()).zza(new zzao(this.zzhhr, zzan.zzash().zzc((zzci<OnDataPointListener>) this.zzgvn), (PendingIntent) null, zzcbq.zza(taskCompletionSource)));
    }
}

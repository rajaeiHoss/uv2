package com.google.android.gms.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.fitness.data.zzt;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.zzal;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzar;
import com.google.android.gms.internal.zzbxv;
import com.google.android.gms.internal.zzbzg;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzcbq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends zzdo<zzbxv, OnDataPointListener> {
    private /* synthetic */ zzci zzgvn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzo(SensorsClient sensorsClient, zzck zzck, zzci zzci) {
        super(zzck);
        this.zzgvn = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzbxv zzbxv, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzal zzd = zzan.zzash().zzd(this.zzgvn);
        if (zzd == null) {
            taskCompletionSource.setResult(false);
            return;
        }
        ((zzbzg) zzbxv.zzalw()).zza(new zzar((zzt) zzd, (PendingIntent) null, (zzbzt) zzcbq.zzb(taskCompletionSource)));
    }
}

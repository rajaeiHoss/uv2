package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zza;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzbh;
import com.google.android.gms.fitness.request.zzd;
import com.google.android.gms.internal.zzbws;
import com.google.android.gms.internal.zzbyw;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzcbq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzc extends zzdo<zzbws, BleScanCallback> {
    private /* synthetic */ zzci zzgvn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzc(BleClient bleClient, zzck zzck, zzci zzci) {
        super(zzck);
        this.zzgvn = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzbws zzbws, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zza zzb2 = zzd.zzasf().zzb((zzci<BleScanCallback>) this.zzgvn);
        if (zzb2 == null) {
            taskCompletionSource.setResult(false);
            return;
        }
        ((zzbyw) zzbws.zzalw()).zza(new zzbh((zzae) zzb2, (zzbzt) zzcbq.zzb(taskCompletionSource)));
    }
}

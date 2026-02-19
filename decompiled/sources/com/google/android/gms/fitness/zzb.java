package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzd;
import com.google.android.gms.internal.zzbws;
import com.google.android.gms.internal.zzbyw;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzcbq;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzb extends zzcq<zzbws, BleScanCallback> {
    private /* synthetic */ zzci zzgvn;
    private /* synthetic */ List zzhhc;
    private /* synthetic */ int zzhhd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzb(BleClient bleClient, zzci zzci, zzci zzci2, List list, int i) {
        super(zzci);
        this.zzgvn = zzci2;
        this.zzhhc = list;
        this.zzhhd = i;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzbws zzbws, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new StartBleScanRequest((List<DataType>) this.zzhhc, (zzae) zzd.zzasf().zza((zzci<BleScanCallback>) this.zzgvn), this.zzhhd, (zzbzt) zzcbq.zza(taskCompletionSource)));
    }
}

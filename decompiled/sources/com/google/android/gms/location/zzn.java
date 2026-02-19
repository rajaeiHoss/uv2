package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.internal.zzcgr;
import com.google.android.gms.internal.zzchh;
import com.google.android.gms.internal.zzchl;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzn extends zzcq<zzchh, LocationCallback> {
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ zzchl zzirm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzn(FusedLocationProviderClient fusedLocationProviderClient, zzci zzci, zzchl zzchl, zzci zzci2) {
        super(zzci);
        this.zzirm = zzchl;
        this.zzhsp = zzci2;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzchh zzchh, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        zzchh.zza(this.zzirm, (zzci<LocationCallback>) this.zzhsp, (zzcgr) new FusedLocationProviderClient.zza(taskCompletionSource));
    }
}

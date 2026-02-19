package com.google.android.gms.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.internal.zzchh;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo extends zzdo<zzchh, LocationCallback> {
    private /* synthetic */ FusedLocationProviderClient zzirn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzo(FusedLocationProviderClient fusedLocationProviderClient, zzck zzck) {
        super(zzck);
        this.zzirn = fusedLocationProviderClient;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzchh zzchh, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        try {
            zzchh.zzb(zzakx(), this.zzirn.zzc(taskCompletionSource));
        } catch (RuntimeException e) {
            taskCompletionSource.trySetException(e);
        }
    }
}

package com.google.android.gms.location;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzchh;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzl extends zzde<zzchh, Location> {
    zzl(FusedLocationProviderClient fusedLocationProviderClient) {
    }

    /* access modifiers changed from: protected */
    public final void zza(zzchh zzchh, TaskCompletionSource<Location> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(zzchh.getLastLocation());
    }
}

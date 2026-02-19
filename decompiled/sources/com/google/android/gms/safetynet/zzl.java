package com.google.android.gms.safetynet;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzcxd;
import com.google.android.gms.internal.zzcxs;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzl extends zzde<zzcxs, Void> {
    zzl(SafetyNetClient safetyNetClient) {
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcxs zzcxs, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzcxd) zzcxs.zzalw()).zza(new zzm(this, taskCompletionSource));
    }
}

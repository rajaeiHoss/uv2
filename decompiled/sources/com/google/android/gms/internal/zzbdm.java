package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcb;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbdm extends zzcb {
    private /* synthetic */ TaskCompletionSource zzeuo;

    zzbdm(zzbdl zzbdl, TaskCompletionSource taskCompletionSource) {
        this.zzeuo = taskCompletionSource;
    }

    public final void zzn(Status status) throws RemoteException {
        zzdf.zza(status, null, this.zzeuo);
    }
}

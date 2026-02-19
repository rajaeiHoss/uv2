package com.google.android.gms.fido.u2f;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.internal.zzbvv;
import com.google.android.gms.internal.zzbvx;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzb extends zzbvx {
    private /* synthetic */ TaskCompletionSource zzhec;

    zzb(zza zza, TaskCompletionSource taskCompletionSource) {
        this.zzhec = taskCompletionSource;
    }

    public final void zza(Status status, PendingIntent pendingIntent) throws RemoteException {
        zzdf.zza(status, new zzbvv(pendingIntent), this.zzhec);
    }
}

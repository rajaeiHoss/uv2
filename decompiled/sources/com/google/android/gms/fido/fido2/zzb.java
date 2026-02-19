package com.google.android.gms.fido.fido2;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.internal.zzbvi;
import com.google.android.gms.internal.zzbvs;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzb extends zzbvs {
    private /* synthetic */ TaskCompletionSource zzhec;

    zzb(zza zza, TaskCompletionSource taskCompletionSource) {
        this.zzhec = taskCompletionSource;
    }

    public final void zza(Status status, PendingIntent pendingIntent) throws RemoteException {
        zzdf.zza(status, new zzbvi(pendingIntent), this.zzhec);
    }
}

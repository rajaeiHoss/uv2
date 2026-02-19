package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbuf extends zzbtx<Void> {
    public zzbuf(TaskCompletionSource<Void> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void onSuccess() throws RemoteException {
        zzdf.zza(Status.zzftq, null, zzaqt());
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbty extends zzbtx<Boolean> {
    public zzbty(TaskCompletionSource<Boolean> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void onSuccess() throws RemoteException {
        zzaqt().setResult(true);
    }
}

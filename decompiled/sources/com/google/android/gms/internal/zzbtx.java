package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zzbtx<T> extends zzbma {
    private TaskCompletionSource<T> zzgyc;

    zzbtx(TaskCompletionSource<T> taskCompletionSource) {
        this.zzgyc = taskCompletionSource;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgyc.setException(new ApiException(status));
    }

    public final TaskCompletionSource<T> zzaqt() {
        return this.zzgyc;
    }
}

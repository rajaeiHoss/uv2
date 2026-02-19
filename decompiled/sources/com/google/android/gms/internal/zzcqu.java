package com.google.android.gms.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcqu<T> implements zzn<T> {
    private final TaskCompletionSource<Void> zzjys;

    zzcqu(zzcpz zzcpz, TaskCompletionSource<Void> taskCompletionSource) {
        this.zzjys = taskCompletionSource;
    }

    public final void setResult(T t) {
        this.zzjys.setResult(null);
    }

    public final void zzu(Status status) {
        this.zzjys.setException(new ApiException(status));
    }
}

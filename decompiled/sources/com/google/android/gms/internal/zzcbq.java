package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzcbq extends zzbzu {
    private final zzn<Status> zzhmf;

    public zzcbq(zzn<Status> zzn) {
        this.zzhmf = zzn;
    }

    public static zzcbq zza(TaskCompletionSource<Void> taskCompletionSource) {
        return new zzcbq(new zzcbr(taskCompletionSource));
    }

    public static zzcbq zzb(TaskCompletionSource<Boolean> taskCompletionSource) {
        return new zzcbq(new zzcbs(taskCompletionSource));
    }

    public final void zzn(Status status) {
        this.zzhmf.setResult(status);
    }
}

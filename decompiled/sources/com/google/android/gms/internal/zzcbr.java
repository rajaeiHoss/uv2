package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzcbr implements zzn<Status> {
    private /* synthetic */ TaskCompletionSource zzhnm;

    zzcbr(TaskCompletionSource taskCompletionSource) {
        this.zzhnm = taskCompletionSource;
    }

    public final void setResult(Status status) {
        zzdf.zza(status, null, this.zzhnm);
    }

    public final void zzu(Status status) {
        throw new UnsupportedOperationException("This method should never get invoked");
    }
}

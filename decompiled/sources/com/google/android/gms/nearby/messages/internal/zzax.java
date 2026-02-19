package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzax implements zzn<Status> {
    private /* synthetic */ TaskCompletionSource zzeuo;

    zzax(zzak zzak, TaskCompletionSource taskCompletionSource) {
        this.zzeuo = taskCompletionSource;
    }

    public final void setResult(Status status) {
        this.zzeuo.setResult(null);
    }

    public final void zzu(Status status) {
        this.zzeuo.setException(new ApiException(status));
    }
}

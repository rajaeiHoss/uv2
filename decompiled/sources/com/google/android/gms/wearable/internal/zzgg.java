package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzgg implements zzn<Status> {
    private TaskCompletionSource<Boolean> zzlvi;

    zzgg(TaskCompletionSource<Boolean> taskCompletionSource) {
        this.zzlvi = taskCompletionSource;
    }

    public final void setResult(Status status) {
        TaskCompletionSource<Boolean> taskCompletionSource;
        boolean z;
        int statusCode = status.getStatusCode();
        if (statusCode == 0) {
            taskCompletionSource = this.zzlvi;
            z = true;
        } else if (statusCode == 4002) {
            taskCompletionSource = this.zzlvi;
            z = false;
        } else {
            zzu(status);
            return;
        }
        taskCompletionSource.setResult(Boolean.valueOf(z));
    }

    public final void zzu(Status status) {
        this.zzlvi.setException(new ApiException(status));
    }
}

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzay implements OnCompleteListener<Boolean> {
    private /* synthetic */ TaskCompletionSource zzhnm;

    zzay(zzak zzak, TaskCompletionSource taskCompletionSource) {
        this.zzhnm = taskCompletionSource;
    }

    public final void onComplete(Task<Boolean> task) {
        if (task.isSuccessful()) {
            this.zzhnm.setResult(null);
        } else {
            this.zzhnm.setException(task.getException());
        }
    }
}

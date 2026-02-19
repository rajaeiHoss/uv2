package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzf implements OnFailureListener {
    private /* synthetic */ TaskCompletionSource zzouz;

    zzf(StorageReference storageReference, TaskCompletionSource taskCompletionSource) {
        this.zzouz = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        this.zzouz.setException(exc);
    }
}

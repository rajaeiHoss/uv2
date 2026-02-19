package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzg implements OnFailureListener {
    private /* synthetic */ TaskCompletionSource zzova;

    zzg(StorageReference storageReference, TaskCompletionSource taskCompletionSource) {
        this.zzova = taskCompletionSource;
    }

    public final void onFailure(Exception exc) {
        this.zzova.setException(StorageException.fromExceptionAndHttpCode(exc, 0));
    }
}

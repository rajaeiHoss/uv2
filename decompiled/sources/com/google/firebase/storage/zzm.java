package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

final class zzm<TResult extends StorageTask.ProvideError> implements zzab<OnProgressListener<? super TResult>, TResult> {
    zzm(StorageTask<TResult> storageTask) {
    }

    public final void zzi(OnProgressListener<? super TResult> onProgressListener, TResult tresult) {
        onProgressListener.onProgress(tresult);
    }
}

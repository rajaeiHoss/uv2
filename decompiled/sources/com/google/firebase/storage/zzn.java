package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;

final class zzn<TResult extends StorageTask.ProvideError> implements zzab<OnPausedListener<? super TResult>, TResult> {
    zzn(StorageTask<TResult> storageTask) {
    }

    public final void zzi(OnPausedListener<? super TResult> onPausedListener, TResult tresult) {
        onPausedListener.onPaused(tresult);
    }
}

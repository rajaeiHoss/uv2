package com.google.firebase.storage;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.storage.StorageTask;

final class zzk<TResult extends StorageTask.ProvideError> implements zzab<OnFailureListener, TResult> {
    private /* synthetic */ StorageTask<TResult> zzovk;

    zzk(StorageTask<TResult> storageTask) {
        this.zzovk = storageTask;
    }

    public final void zzi(OnFailureListener onFailureListener, TResult tresult) {
        zzt.zzcod().zzc(this.zzovk);
        onFailureListener.onFailure(tresult.getError());
    }
}

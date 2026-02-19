package com.google.firebase.storage;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageTask;

final class zzj<TResult extends StorageTask.ProvideError> implements zzab<OnSuccessListener<? super TResult>, TResult> {
    private /* synthetic */ StorageTask<TResult> zzovk;

    zzj(StorageTask<TResult> storageTask) {
        this.zzovk = storageTask;
    }

    public final void zzi(OnSuccessListener<? super TResult> onSuccessListener, TResult tresult) {
        zzt.zzcod().zzc(this.zzovk);
        onSuccessListener.onSuccess(tresult);
    }
}

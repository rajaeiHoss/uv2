package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCompleteListener;

final class zzl<TResult extends StorageTask.ProvideError> implements zzab<OnCompleteListener<TResult>, TResult> {
    private /* synthetic */ StorageTask<TResult> zzovk;

    zzl(StorageTask<TResult> storageTask) {
        this.zzovk = storageTask;
    }

    public final void zzi(OnCompleteListener<TResult> onCompleteListener, TResult tresult) {
        zzt.zzcod().zzc(this.zzovk);
        onCompleteListener.onComplete(this.zzovk);
    }
}

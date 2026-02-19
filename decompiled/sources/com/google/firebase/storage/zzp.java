package com.google.firebase.storage;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.RuntimeExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp<TResult extends StorageTask.ProvideError, TContinuationResult> implements OnCompleteListener<TResult> {
    final /* synthetic */ TaskCompletionSource<TContinuationResult> zzghr;
    private /* synthetic */ StorageTask<TResult> zzovk;
    private /* synthetic */ Continuation<TResult, Task<TContinuationResult>> zzovl;

    zzp(StorageTask<TResult> storageTask, Continuation<TResult, Task<TContinuationResult>> continuation, TaskCompletionSource<TContinuationResult> taskCompletionSource) {
        this.zzovk = storageTask;
        this.zzovl = continuation;
        this.zzghr = taskCompletionSource;
    }

    public final void onComplete(Task<TResult> task) {
        try {
            Task<TContinuationResult> task2 = (Task<TContinuationResult>) this.zzovl.then(this.zzovk);
            if (this.zzghr.getTask().isComplete()) {
                return;
            }
            if (task2 == null) {
                this.zzghr.setException(new NullPointerException("Continuation returned null"));
                return;
            }
            task2.addOnSuccessListener(new zzq<>(this));
            task2.addOnFailureListener(new zzr(this));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzghr.setException((Exception) e.getCause());
            } else {
                this.zzghr.setException(e);
            }
        } catch (Exception e2) {
            this.zzghr.setException(e2);
        }
    }
}

package com.google.firebase.storage;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzo<TResult extends StorageTask.ProvideError, TContinuationResult> implements OnCompleteListener<TResult> {
    private /* synthetic */ TaskCompletionSource<TContinuationResult> zzghr;
    private /* synthetic */ StorageTask<TResult> zzovk;
    private /* synthetic */ Continuation<TResult, TContinuationResult> zzovl;

    zzo(StorageTask<TResult> storageTask, Continuation<TResult, TContinuationResult> continuation, TaskCompletionSource<TContinuationResult> taskCompletionSource) {
        this.zzovk = storageTask;
        this.zzovl = continuation;
        this.zzghr = taskCompletionSource;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: com.google.android.gms.tasks.RuntimeExecutionException} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: com.google.android.gms.tasks.RuntimeExecutionException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(com.google.android.gms.tasks.Task<TResult> r2) {
        /*
            r1 = this;
            com.google.android.gms.tasks.Continuation r2 = r1.zzovl     // Catch:{ RuntimeExecutionException -> 0x0021, Exception -> 0x001a }
            com.google.firebase.storage.StorageTask r0 = r1.zzovk     // Catch:{ RuntimeExecutionException -> 0x0021, Exception -> 0x001a }
            java.lang.Object r2 = r2.then(r0)     // Catch:{ RuntimeExecutionException -> 0x0021, Exception -> 0x001a }
            com.google.android.gms.tasks.TaskCompletionSource r0 = r1.zzghr
            com.google.android.gms.tasks.Task r0 = r0.getTask()
            boolean r0 = r0.isComplete()
            if (r0 != 0) goto L_0x0019
            com.google.android.gms.tasks.TaskCompletionSource r0 = r1.zzghr
            r0.setResult(r2)
        L_0x0019:
            return
        L_0x001a:
            r2 = move-exception
        L_0x001b:
            com.google.android.gms.tasks.TaskCompletionSource r0 = r1.zzghr
        L_0x001d:
            r0.setException(r2)
            return
        L_0x0021:
            r2 = move-exception
            java.lang.Throwable r0 = r2.getCause()
            boolean r0 = r0 instanceof java.lang.Exception
            if (r0 == 0) goto L_0x001b
            com.google.android.gms.tasks.TaskCompletionSource r0 = r1.zzghr
            java.lang.Throwable r2 = r2.getCause()
            java.lang.Exception r2 = (java.lang.Exception) r2
            goto L_0x001d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.zzo.onComplete(com.google.android.gms.tasks.Task):void");
    }
}

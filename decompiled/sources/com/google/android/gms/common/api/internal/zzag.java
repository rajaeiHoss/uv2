package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzag<TResult> implements OnCompleteListener<TResult> {
    private /* synthetic */ TaskCompletionSource<TResult> zzeuo;
    private /* synthetic */ zzae zzfwz;

    zzag(zzae zzae, TaskCompletionSource<TResult> taskCompletionSource) {
        this.zzfwz = zzae;
        this.zzeuo = taskCompletionSource;
    }

    public final void onComplete(Task<TResult> task) {
        this.zzfwz.zzfwx.remove(this.zzeuo);
    }
}

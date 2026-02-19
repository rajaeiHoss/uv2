package com.google.firebase.appindexing.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzp implements OnCompleteListener<Void> {
    private /* synthetic */ TaskCompletionSource zzeuo;
    private /* synthetic */ zzk zzmoi;
    private /* synthetic */ zzm zzmoj;

    zzp(zzm zzm, zzk zzk, TaskCompletionSource taskCompletionSource) {
        this.zzmoj = zzm;
        this.zzmoi = zzk;
        this.zzeuo = taskCompletionSource;
    }

    public final void onComplete(Task<Void> task) {
        this.zzmoj.zza(this.zzmoi, this.zzeuo, 0);
    }
}

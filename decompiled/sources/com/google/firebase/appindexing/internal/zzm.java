package com.google.firebase.appindexing.internal;

import android.os.Handler;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

final class zzm implements OnCompleteListener<Void>, Executor {
    /* access modifiers changed from: private */
    public final Handler handler;
    private final GoogleApi<?> zzmog;
    private Task<Void> zzmoh = null;

    public zzm(GoogleApi<?> googleApi) {
        this.zzmog = googleApi;
        this.handler = new Handler(googleApi.getLooper());
    }

    /* access modifiers changed from: private */
    public final void zza(zzk zzk, TaskCompletionSource<Void> taskCompletionSource, int i) {
        this.zzmog.zzb(zzk).addOnCompleteListener((Executor) this, new zzn(this, i, zzk, taskCompletionSource));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r2 = r2.getResult().getStatusCode();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zzf(com.google.android.gms.tasks.Task<com.google.android.gms.common.api.Status> r2) {
        /*
            boolean r0 = r2.isSuccessful()
            r1 = 0
            if (r0 == 0) goto L_0x001b
            java.lang.Object r2 = r2.getResult()
            com.google.android.gms.common.api.Status r2 = (com.google.android.gms.common.api.Status) r2
            int r2 = r2.getStatusCode()
            r0 = 17600(0x44c0, float:2.4663E-41)
            if (r2 < r0) goto L_0x001b
            r0 = 17649(0x44f1, float:2.4732E-41)
            if (r2 > r0) goto L_0x001b
            r2 = 1
            return r2
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzm.zzf(com.google.android.gms.tasks.Task):boolean");
    }

    public final void execute(Runnable runnable) {
        this.handler.post(runnable);
    }

    public final synchronized void onComplete(Task<Void> task) {
        if (task == this.zzmoh) {
            this.zzmoh = null;
        }
    }

    public final Task<Void> zzb(zzk zzk) {
        Task<Void> task;
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task<Void> task2 = taskCompletionSource.getTask();
        synchronized (this) {
            task = this.zzmoh;
            this.zzmoh = task2;
        }
        task2.addOnCompleteListener((Executor) this, (OnCompleteListener<Void>) this);
        if (task == null) {
            zza(zzk, taskCompletionSource, 0);
        } else {
            task.addOnCompleteListener((Executor) this, (OnCompleteListener<Void>) new zzp(this, zzk, taskCompletionSource));
        }
        return task2;
    }
}

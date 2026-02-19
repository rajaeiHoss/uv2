package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzae {
    /* access modifiers changed from: private */
    public final Map<BasePendingResult<?>, Boolean> zzfww = Collections.synchronizedMap(new WeakHashMap());
    /* access modifiers changed from: private */
    public final Map<TaskCompletionSource<?>, Boolean> zzfwx = Collections.synchronizedMap(new WeakHashMap());

    private final void zza(boolean z, Status status) {
        HashMap<BasePendingResult<?>, Boolean> pendingSnapshot;
        HashMap<TaskCompletionSource<?>, Boolean> taskSnapshot;
        synchronized (this.zzfww) {
            pendingSnapshot = new HashMap<>(this.zzfww);
        }
        synchronized (this.zzfwx) {
            taskSnapshot = new HashMap<>(this.zzfwx);
        }
        for (Map.Entry<BasePendingResult<?>, Boolean> entry : pendingSnapshot.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                entry.getKey().zzv(status);
            }
        }
        for (Map.Entry<TaskCompletionSource<?>, Boolean> entry2 : taskSnapshot.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                entry2.getKey().trySetException(new ApiException(status));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(BasePendingResult<? extends Result> basePendingResult, boolean z) {
        this.zzfww.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.zza(new zzaf(this, basePendingResult));
    }

    /* access modifiers changed from: package-private */
    public final <TResult> void zza(TaskCompletionSource<TResult> taskCompletionSource, boolean z) {
        this.zzfwx.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zzag(this, taskCompletionSource));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzajc() {
        return !this.zzfww.isEmpty() || !this.zzfwx.isEmpty();
    }

    public final void zzajd() {
        zza(false, zzbm.zzfzg);
    }

    public final void zzaje() {
        zza(true, zzdk.zzgbq);
    }
}

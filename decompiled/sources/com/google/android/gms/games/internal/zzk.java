package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzk implements PendingResult.zza {
    private final TaskCompletionSource zzhzp;
    private final zzbo zzhzw;
    private final PendingResult zzhzx;

    zzk(zzbo zzbo, PendingResult pendingResult, TaskCompletionSource taskCompletionSource) {
        this.zzhzw = zzbo;
        this.zzhzx = pendingResult;
        this.zzhzp = taskCompletionSource;
    }

    public final void zzr(Status status) {
        zzg.zza(this.zzhzw, this.zzhzx, this.zzhzp, status);
    }
}

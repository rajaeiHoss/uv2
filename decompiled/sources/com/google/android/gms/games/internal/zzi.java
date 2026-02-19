package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzi implements PendingResult.zza {
    private final PendingResult zzhzn;
    private final TaskCompletionSource zzhzt;
    private final zzbo zzhzu;

    zzi(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo) {
        this.zzhzn = pendingResult;
        this.zzhzt = taskCompletionSource;
        this.zzhzu = zzbo;
    }

    public final void zzr(Status status) {
        zzg.zza(this.zzhzn, this.zzhzt, this.zzhzu, status);
    }
}

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzj implements PendingResult.zza {
    private final PendingResult zzhzn;
    private final TaskCompletionSource zzhzt;
    private final zzbo zzhzu;
    private final zzo zzhzv;

    zzj(PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo, zzo zzo) {
        this.zzhzn = pendingResult;
        this.zzhzt = taskCompletionSource;
        this.zzhzu = zzbo;
        this.zzhzv = zzo;
    }

    public final void zzr(Status status) {
        zzg.zza(this.zzhzn, this.zzhzt, this.zzhzu, this.zzhzv, status);
    }
}

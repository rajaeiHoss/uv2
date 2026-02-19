package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzl implements PendingResult.zza {
    private final TaskCompletionSource zzhzp;
    private final zzbo zzhzq;
    private final PendingResult zzhzx;
    private final zzp zzhzy;

    zzl(zzp zzp, PendingResult pendingResult, TaskCompletionSource taskCompletionSource, zzbo zzbo) {
        this.zzhzy = zzp;
        this.zzhzx = pendingResult;
        this.zzhzp = taskCompletionSource;
        this.zzhzq = zzbo;
    }

    public final void zzr(Status status) {
        zzg.zza(this.zzhzy, this.zzhzx, this.zzhzp, this.zzhzq, status);
    }
}

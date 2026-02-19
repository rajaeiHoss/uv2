package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tasks.TaskCompletionSource;

final /* synthetic */ class zzh implements PendingResult.zza {
    private final PendingResult zzhzn;
    private final zzp zzhzo;
    private final TaskCompletionSource zzhzp;
    private final zzbo zzhzq;
    private final zzbo zzhzr;
    private final zzn zzhzs;

    zzh(PendingResult pendingResult, zzp zzp, TaskCompletionSource taskCompletionSource, zzbo zzbo, zzbo zzbo2, zzn zzn) {
        this.zzhzn = pendingResult;
        this.zzhzo = zzp;
        this.zzhzp = taskCompletionSource;
        this.zzhzq = zzbo;
        this.zzhzr = zzbo2;
        this.zzhzs = zzn;
    }

    public final void zzr(Status status) {
        zzg.zza(this.zzhzn, this.zzhzo, this.zzhzp, this.zzhzq, this.zzhzr, this.zzhzs, status);
    }
}

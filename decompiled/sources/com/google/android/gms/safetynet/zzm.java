package com.google.android.gms.safetynet;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.internal.zzcwz;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzm extends zzcwz {
    private /* synthetic */ TaskCompletionSource zzeuo;

    zzm(zzl zzl, TaskCompletionSource taskCompletionSource) {
        this.zzeuo = taskCompletionSource;
    }

    public final void zzao(Status status) {
        zzdf.zza(status, null, this.zzeuo);
    }
}

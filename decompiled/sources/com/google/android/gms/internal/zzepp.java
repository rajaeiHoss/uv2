package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

final class zzepp extends zzepo {
    private TaskCompletionSource<ShortDynamicLink> zzejm;

    zzepp(TaskCompletionSource<ShortDynamicLink> taskCompletionSource) {
        this.zzejm = taskCompletionSource;
    }

    public final void zza(Status status, zzepx zzepx) {
        zzdf.zza(status, zzepx, this.zzejm);
    }
}

package com.google.android.gms.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final /* synthetic */ class zzbpc implements Continuation {
    private final zzblv zzgvh;

    zzbpc(zzblv zzblv) {
        this.zzgvh = zzblv;
    }

    public final Object then(Task task) throws Exception {
        return zzboz.zza(this.zzgvh, task);
    }
}

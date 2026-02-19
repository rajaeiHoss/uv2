package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final /* synthetic */ class zzbpb implements Continuation {
    private final zzci zzgvg;

    zzbpb(zzci zzci) {
        this.zzgvg = zzci;
    }

    public final Object then(Task task) {
        try {
            return zzboz.zza(this.zzgvg, task);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

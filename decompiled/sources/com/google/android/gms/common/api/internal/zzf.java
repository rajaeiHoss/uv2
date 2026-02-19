package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzf extends zzb<Boolean> {
    private zzck<?> zzfuc;

    public zzf(zzck<?> zzck, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zzfuc = zzck;
    }

    public final /* bridge */ /* synthetic */ void zza(zzae zzae, boolean z) {
    }

    public final /* bridge */ /* synthetic */ void zza(RuntimeException runtimeException) {
        super.zza(runtimeException);
    }

    public final void zzb(zzbo<?> zzbo) throws RemoteException {
        zzcr remove = zzbo.zzakh().remove(this.zzfuc);
        if (remove != null) {
            remove.zzftz.zzc(zzbo.zzaix(), this.zzejm);
            remove.zzfty.zzaky();
            return;
        }
        this.zzejm.trySetResult(false);
    }

    public final /* bridge */ /* synthetic */ void zzs(Status status) {
        super.zzs(status);
    }
}

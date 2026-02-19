package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzba<T> extends zzdo<zzah, T> {
    private /* synthetic */ zzak zzkdp;
    private /* synthetic */ zzbd zzkdr;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzba(zzak zzak, zzck<T> zzck, zzbd zzbd) {
        super(zzck);
        this.zzkdp = zzak;
        this.zzkdr = zzbd;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzah zzah, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        this.zzkdr.zza(zzah, this.zzkdp.zzd(taskCompletionSource));
    }
}

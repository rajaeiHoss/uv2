package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaz<T> extends zzcq<zzah, T> {
    private /* synthetic */ zzak zzkdp;
    private /* synthetic */ zzbd zzkdq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaz(zzak zzak, zzci<T> zzci, zzbd zzbd) {
        super(zzci);
        this.zzkdp = zzak;
        this.zzkdq = zzbd;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzah zzah, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzkdq.zza(zzah, this.zzkdp.zzd(taskCompletionSource));
    }
}

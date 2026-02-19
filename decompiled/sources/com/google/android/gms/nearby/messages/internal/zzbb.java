package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbb extends zzde<zzah, Void> {
    private /* synthetic */ zzak zzkdp;
    private /* synthetic */ zzbd zzkds;

    zzbb(zzak zzak, zzbd zzbd) {
        this.zzkdp = zzak;
        this.zzkds = zzbd;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        this.zzkds.zza(zzah, this.zzkdp.zzd(taskCompletionSource));
    }
}

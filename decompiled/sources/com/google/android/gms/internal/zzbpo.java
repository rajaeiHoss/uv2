package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.drive.events.OpenFileCallback;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpo extends zzdo<zzbnq, OpenFileCallback> {
    private /* synthetic */ zzblv zzgvm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbpo(zzboz zzboz, zzck zzck, zzblv zzblv) {
        super(zzck);
        this.zzgvm = zzblv;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzbnq zzbnq, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        taskCompletionSource.setResult(Boolean.valueOf(this.zzgvm.cancel()));
    }
}

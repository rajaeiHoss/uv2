package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzdmv;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzai extends zzde<zzdmv, Boolean> {
    private /* synthetic */ IsReadyToPayRequest zzlno;

    zzai(PaymentsClient paymentsClient, IsReadyToPayRequest isReadyToPayRequest) {
        this.zzlno = isReadyToPayRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        zzdmv.zza(this.zzlno, taskCompletionSource);
    }
}

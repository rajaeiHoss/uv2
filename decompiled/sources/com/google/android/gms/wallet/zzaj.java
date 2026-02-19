package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzdmv;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaj extends zzde<zzdmv, PaymentData> {
    private /* synthetic */ PaymentDataRequest zzlnp;

    zzaj(PaymentsClient paymentsClient, PaymentDataRequest paymentDataRequest) {
        this.zzlnp = paymentDataRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv, TaskCompletionSource<PaymentData> taskCompletionSource) throws RemoteException {
        zzdmv.zza(this.zzlnp, taskCompletionSource);
    }
}

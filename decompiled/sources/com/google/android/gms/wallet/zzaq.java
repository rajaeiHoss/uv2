package com.google.android.gms.wallet;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.internal.zzdmv;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzaq extends zzde<zzdmv, AutoResolvableVoidResult> {
    private /* synthetic */ CreateWalletObjectsRequest zzlog;

    zzaq(WalletObjectsClient walletObjectsClient, CreateWalletObjectsRequest createWalletObjectsRequest) {
        this.zzlog = createWalletObjectsRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzdmv zzdmv, TaskCompletionSource<AutoResolvableVoidResult> taskCompletionSource) throws RemoteException {
        zzdmv.zza(this.zzlog, taskCompletionSource);
    }
}

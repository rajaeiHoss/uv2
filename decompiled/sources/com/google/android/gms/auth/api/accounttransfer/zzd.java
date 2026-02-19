package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawt;

final class zzd extends AccountTransferClient.zzc {
    private /* synthetic */ zzawt zzeje;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzd(AccountTransferClient accountTransferClient, zzawt zzawt) {
        super((AccountTransferClient.zzc) null);
        this.zzeje = zzawt;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zzejn, this.zzeje);
    }
}

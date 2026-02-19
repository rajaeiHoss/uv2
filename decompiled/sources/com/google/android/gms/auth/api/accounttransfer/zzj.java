package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawp;

final class zzj extends AccountTransferClient.zzc {
    private /* synthetic */ zzawp zzejk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzj(AccountTransferClient accountTransferClient, zzawp zzawp) {
        super((AccountTransferClient.zzc) null);
        this.zzejk = zzawp;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zzejn, this.zzejk);
    }
}

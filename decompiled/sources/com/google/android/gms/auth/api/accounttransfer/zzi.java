package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawv;

final class zzi extends AccountTransferClient.zzc {
    private /* synthetic */ zzawv zzejj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzi(AccountTransferClient accountTransferClient, zzawv zzawv) {
        super((AccountTransferClient.zzc) null);
        this.zzejj = zzawv;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) this.zzejn, this.zzejj);
    }
}

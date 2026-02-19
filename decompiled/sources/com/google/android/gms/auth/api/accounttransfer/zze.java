package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;
import com.google.android.gms.internal.zzawr;

final class zze extends AccountTransferClient.zzb<byte[]> {
    private /* synthetic */ zzawr zzejf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zze(AccountTransferClient accountTransferClient, zzawr zzawr) {
        super((AccountTransferClient.zzc) null);
        this.zzejf = zzawr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) new zzf(this, this), this.zzejf);
    }
}

package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.zzawj;
import com.google.android.gms.internal.zzawl;
import com.google.android.gms.internal.zzawn;

final class zzg extends AccountTransferClient.zzb<DeviceMetaData> {
    private /* synthetic */ zzawj zzejh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(AccountTransferClient accountTransferClient, zzawj zzawj) {
        super((AccountTransferClient.zzc) null);
        this.zzejh = zzawj;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawn zzawn) throws RemoteException {
        zzawn.zza((zzawl) new zzh(this, this), this.zzejh);
    }
}

package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;

final class zzf extends AccountTransferClient.zza<byte[]> {
    private /* synthetic */ zze zzejg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzf(zze zze, AccountTransferClient.zzb zzb) {
        super(zzb);
        this.zzejg = zze;
    }

    public final void zzh(byte[] bArr) {
        this.zzejg.setResult(bArr);
    }
}

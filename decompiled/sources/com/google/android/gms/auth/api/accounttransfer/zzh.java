package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;

final class zzh extends AccountTransferClient.zza<DeviceMetaData> {
    private /* synthetic */ zzg zzeji;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzh(zzg zzg, AccountTransferClient.zzb zzb) {
        super(zzb);
        this.zzeji = zzg;
    }

    public final void zza(DeviceMetaData deviceMetaData) {
        this.zzeji.setResult(deviceMetaData);
    }
}

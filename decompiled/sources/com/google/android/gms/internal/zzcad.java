package com.google.android.gms.internal;

import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.fitness.result.BleDevicesResult;

final class zzcad extends zzcbv {
    private final zzn<BleDevicesResult> zzhmf;

    private zzcad(zzn<BleDevicesResult> zzn) {
        this.zzhmf = zzn;
    }

    /* synthetic */ zzcad(zzn zzn, zzbzx zzbzx) {
        this(zzn);
    }

    public final void zza(BleDevicesResult bleDevicesResult) {
        this.zzhmf.setResult(bleDevicesResult);
    }
}

package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.fitness.data.BleDevice;

final class zzb implements zzcl<BleScanCallback> {
    private /* synthetic */ BleDevice zzhnq;

    zzb(zza zza, BleDevice bleDevice) {
        this.zzhnq = bleDevice;
    }

    public final void zzajh() {
    }

    public final void zzu(BleScanCallback bleScanCallback) {
        bleScanCallback.onDeviceFound(this.zzhnq);
    }
}

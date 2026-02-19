package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.wearable.CapabilityApi;

final class zzho implements zzcl<CapabilityApi.CapabilityListener> {
    private /* synthetic */ zzah zzlwg;

    zzho(zzah zzah) {
        this.zzlwg = zzah;
    }

    public final void zzajh() {
    }

    public final void zzu(CapabilityApi.CapabilityListener capabilityListener) {
        capabilityListener.onCapabilityChanged(this.zzlwg);
    }
}

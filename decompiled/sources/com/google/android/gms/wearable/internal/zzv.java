package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzv implements CapabilityApi.CapabilityListener {
    private CapabilityApi.CapabilityListener zzlsp;
    private String zzlsq;

    zzv(CapabilityApi.CapabilityListener capabilityListener, String str) {
        this.zzlsp = capabilityListener;
        this.zzlsq = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzv zzv = (zzv) obj;
        if (!this.zzlsp.equals(zzv.zzlsp)) {
            return false;
        }
        return this.zzlsq.equals(zzv.zzlsq);
    }

    public final int hashCode() {
        return (this.zzlsp.hashCode() * 31) + this.zzlsq.hashCode();
    }

    public final void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        this.zzlsp.onCapabilityChanged(capabilityInfo);
    }
}

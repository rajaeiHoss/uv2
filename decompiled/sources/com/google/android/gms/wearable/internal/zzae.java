package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityClient;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzae implements CapabilityClient.OnCapabilityChangedListener {
    private String zzlsq;
    private CapabilityClient.OnCapabilityChangedListener zzlsv;

    zzae(CapabilityClient.OnCapabilityChangedListener onCapabilityChangedListener, String str) {
        this.zzlsv = onCapabilityChangedListener;
        this.zzlsq = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzae zzae = (zzae) obj;
        if (!this.zzlsv.equals(zzae.zzlsv)) {
            return false;
        }
        return this.zzlsq.equals(zzae.zzlsq);
    }

    public final int hashCode() {
        return (this.zzlsv.hashCode() * 31) + this.zzlsq.hashCode();
    }

    public final void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        this.zzlsv.onCapabilityChanged(capabilityInfo);
    }
}

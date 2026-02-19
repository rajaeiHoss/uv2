package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

public final class zzy implements CapabilityApi.GetCapabilityResult {
    private final Status mStatus;
    private final CapabilityInfo zzlst;

    public zzy(Status status, CapabilityInfo capabilityInfo) {
        this.mStatus = status;
        this.zzlst = capabilityInfo;
    }

    public final CapabilityInfo getCapability() {
        return this.zzlst;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}

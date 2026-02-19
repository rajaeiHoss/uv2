package com.google.android.gms.internal;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

final class zzbdq implements Cast.ApplicationConnectionResult {
    private final String mSessionId;
    private final Status mStatus;
    private final ApplicationMetadata zzfmv;
    private final String zzfmw;
    private final boolean zzfmx;

    public zzbdq(Status status) {
        this(status, (ApplicationMetadata) null, (String) null, (String) null, false);
    }

    public zzbdq(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.mStatus = status;
        this.zzfmv = applicationMetadata;
        this.zzfmw = str;
        this.mSessionId = str2;
        this.zzfmx = z;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzfmv;
    }

    public final String getApplicationStatus() {
        return this.zzfmw;
    }

    public final String getSessionId() {
        return this.mSessionId;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean getWasLaunched() {
        return this.zzfmx;
    }
}

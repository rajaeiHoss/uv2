package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzdin implements Result {
    private final Status mStatus;
    private final int zzbkq;
    private final zzdio zzlbl;
    private final zzdjk zzlbm;

    public zzdin(Status status, int i) {
        this(status, i, (zzdio) null, (zzdjk) null);
    }

    public zzdin(Status status, int i, zzdio zzdio, zzdjk zzdjk) {
        this.mStatus = status;
        this.zzbkq = i;
        this.zzlbl = zzdio;
        this.zzlbm = zzdjk;
    }

    public final int getSource() {
        return this.zzbkq;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final zzdio zzbju() {
        return this.zzlbl;
    }

    public final zzdjk zzbjv() {
        return this.zzlbm;
    }

    public final String zzbjw() {
        int i = this.zzbkq;
        if (i == 0) {
            return "Network";
        }
        if (i == 1) {
            return "Saved file on disk";
        }
        if (i == 2) {
            return "Default resource";
        }
        throw new IllegalStateException("Resource source is unknown.");
    }
}

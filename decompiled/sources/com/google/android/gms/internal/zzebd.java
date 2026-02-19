package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbq;

public final class zzebd extends zzdzc implements Api.ApiOptions.HasOptions {
    private final String zzmna;

    private zzebd(String str) {
        this.zzmna = zzbq.zzh(str, "A valid API key must be provided");
    }

    /* synthetic */ zzebd(String str, zzebc zzebc) {
        this(str);
    }

    public final zzebd clone() {
        return new zzebe(this.zzmna).zzbtz();
    }

    public final String getApiKey() {
        return this.zzmna;
    }

    public final /* synthetic */ zzdzc zzbts() {
        return (zzebd) clone();
    }
}

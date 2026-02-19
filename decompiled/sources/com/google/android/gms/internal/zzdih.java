package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;

final class zzdih<T> {
    private T mData;
    private Status mStatus;
    private long zzlbh;

    public zzdih(Status status, T t, long j) {
        this.mStatus = status;
        this.mData = t;
        this.zzlbh = j;
    }

    public final void zzat(T t) {
        this.mData = t;
    }

    public final void zzav(Status status) {
        this.mStatus = status;
    }

    public final long zzbjs() {
        return this.zzlbh;
    }

    public final void zzbk(long j) {
        this.zzlbh = j;
    }
}

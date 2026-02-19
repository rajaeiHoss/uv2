package com.google.android.gms.internal;

import java.util.concurrent.ScheduledExecutorService;

public final class zzees {
    private final ScheduledExecutorService zzmxn;
    private final zzeeq zzmzv;
    private final zzemn zzmzw;
    private final boolean zzmzx;
    private final String zzmzy;
    private final String zzmzz;
    private final String zznaa;

    public zzees(zzemn zzemn, zzeeq zzeeq, ScheduledExecutorService scheduledExecutorService, boolean z, String str, String str2, String str3) {
        this.zzmzw = zzemn;
        this.zzmzv = zzeeq;
        this.zzmxn = scheduledExecutorService;
        this.zzmzx = z;
        this.zzmzy = str;
        this.zzmzz = str2;
        this.zznaa = str3;
    }

    public final boolean isPersistenceEnabled() {
        return this.zzmzx;
    }

    public final zzemn zzbwk() {
        return this.zzmzw;
    }

    public final zzeeq zzbwl() {
        return this.zzmzv;
    }

    public final ScheduledExecutorService zzbwm() {
        return this.zzmxn;
    }

    public final String zzbwn() {
        return this.zzmzy;
    }

    public final String zzbwo() {
        return this.zzmzz;
    }

    public final String zzbwp() {
        return this.zznaa;
    }
}

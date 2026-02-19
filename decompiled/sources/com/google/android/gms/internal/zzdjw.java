package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;

public final class zzdjw extends zzdjq<zzdjq<?>> {
    public static final zzdjw zzlcw = new zzdjw("BREAK");
    public static final zzdjw zzlcx = new zzdjw("CONTINUE");
    public static final zzdjw zzlcy = new zzdjw("NULL");
    public static final zzdjw zzlcz = new zzdjw("UNDEFINED");
    private final String mName;
    private final boolean zzlda;
    private final zzdjq<?> zzldb;

    public zzdjw(zzdjq<?> zzdjq) {
        zzbq.checkNotNull(zzdjq);
        this.mName = "RETURN";
        this.zzlda = true;
        this.zzldb = zzdjq;
    }

    private zzdjw(String str) {
        this.mName = str;
        this.zzlda = false;
        this.zzldb = null;
    }

    public final String toString() {
        return this.mName;
    }

    public final zzdjq<?> value() {
        return this.zzldb;
    }

    public final boolean zzbkq() {
        return this.zzlda;
    }
}

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;

public final class zzcyk implements Api.ApiOptions.Optional {
    public static final zzcyk zzklp = new zzcyk(false, false, (String) null, false, (String) null, false, (Long) null, (Long) null);
    private final boolean zzela = false;
    private final String zzelb = null;
    private final boolean zzenc = false;
    private final String zzend = null;
    private final boolean zzklq = false;
    private final boolean zzklr = false;
    private final Long zzkls = null;
    private final Long zzklt = null;

    static {
        new zzcyl();
    }

    private zzcyk(boolean z, boolean z2, String str, boolean z3, String str2, boolean z4, Long l, Long l2) {
    }

    public final String getServerClientId() {
        return this.zzelb;
    }

    public final boolean isIdTokenRequested() {
        return this.zzela;
    }

    public final boolean zzbeu() {
        return this.zzklq;
    }

    public final boolean zzbev() {
        return this.zzenc;
    }

    public final String zzbew() {
        return this.zzend;
    }

    public final boolean zzbex() {
        return this.zzklr;
    }

    public final Long zzbey() {
        return this.zzkls;
    }

    public final Long zzbez() {
        return this.zzklt;
    }
}

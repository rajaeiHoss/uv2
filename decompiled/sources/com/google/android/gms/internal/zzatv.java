package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;

public final class zzatv {
    private static Api.zzf<zzaux> zzefb = new Api.zzf<>();
    private static final Api.zza<zzaux, Api.ApiOptions.NoOptions> zzefc;
    public static final Api<Api.ApiOptions.NoOptions> zzefd;
    private static zzaur zzefe = new zzauz();

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.gms.internal.zzauz, com.google.android.gms.internal.zzaur] */
    static {
        zzatw zzatw = new zzatw();
        zzefc = zzatw;
        zzefd = new Api<>("AppDataSearch.LIGHTWEIGHT_API", zzatw, zzefb);
    }
}

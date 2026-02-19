package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;

public final class zzbgs {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final Api.zzf<zzbha> zzegu;
    private static final Api.zza<zzbha, Api.ApiOptions.NoOptions> zzegv;
    public static final zzbgu zzgif = new zzbgv();

    static {
        Api.zzf<zzbha> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzbgt zzbgt = new zzbgt();
        zzegv = zzbgt;
        API = new Api<>("Common.API", zzbgt, zzf);
    }
}

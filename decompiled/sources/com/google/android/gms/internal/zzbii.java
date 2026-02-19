package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;

public final class zzbii {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static Api.zzf<zzbja> zzegu = new Api.zzf<>();
    private static Api.zza<zzbja, Api.ApiOptions.NoOptions> zzegv;
    public static final zzbik zzglu = new zzbis();

    static {
        zzbij zzbij = new zzbij();
        zzegv = zzbij;
        API = new Api<>("Config.API", zzbij, zzegu);
    }
}

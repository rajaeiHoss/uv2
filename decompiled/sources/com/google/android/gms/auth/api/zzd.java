package com.google.android.gms.auth.api;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzaxs;

public final class zzd {
    public static final Api<zzf> API;
    private static Api.zzf<zzaxs> zzeix = new Api.zzf<>();
    private static final Api.zza<zzaxs, zzf> zzeiy;

    static {
        zze zze = new zze();
        zzeiy = zze;
        API = new Api<>("Auth.PROXY_API", zze, zzeix);
    }
}

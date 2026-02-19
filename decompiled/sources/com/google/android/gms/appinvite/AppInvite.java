package com.google.android.gms.appinvite;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzavb;
import com.google.android.gms.internal.zzavk;

@Deprecated
public final class AppInvite {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final AppInviteApi AppInviteApi = new zzavb();
    private static Api.zzf<zzavk> zzegu = new Api.zzf<>();
    private static final Api.zza<zzavk, Api.ApiOptions.NoOptions> zzegv;

    static {
        zza zza = new zza();
        zzegv = zza;
        API = new Api<>("AppInvite.API", zza, zzegu);
    }

    private AppInvite() {
    }
}

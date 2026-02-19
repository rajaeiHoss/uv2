package com.google.android.gms.internal;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class zzcyg {
    public static final Api<zzcyk> API;
    private static Api.zzf<zzcyt> zzegu = new Api.zzf<>();
    public static final Api.zza<zzcyt, zzcyk> zzegv;
    private static Scope zzemx = new Scope(Scopes.PROFILE);
    private static Scope zzemy = new Scope("email");
    private static Api.zzf<zzcyt> zzkln = new Api.zzf<>();
    private static Api.zza<zzcyt, Api.ApiOptions.NoOptions> zzklo = new zzcyi();
    private static Api<Api.ApiOptions.NoOptions> zzgpn = new Api<>("SignIn.INTERNAL_API", zzklo, zzkln);

    static {
        zzcyh zzcyh = new zzcyh();
        zzegv = zzcyh;
        API = new Api<>("SignIn.API", zzcyh, zzegu);
    }
}

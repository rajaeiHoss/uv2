package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;

public final class zzepk extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzepm> zzegu;
    private static final Api.zza<zzepm, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzepm> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzepl zzepl = new zzepl();
        zzegv = zzepl;
        API = new Api<>("DynamicLinks.API", zzepl, zzf);
    }

    public zzepk(Context context) {
        super(context, API, null, GoogleApi.zza.zzfsr);
    }
}

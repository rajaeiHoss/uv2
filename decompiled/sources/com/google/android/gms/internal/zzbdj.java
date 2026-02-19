package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.cast.zzbl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzbdj extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzbdn> zzegu;
    private static final Api.zza<zzbdn, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzbdn> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzbdk zzbdk = new zzbdk();
        zzegv = zzbdk;
        API = new Api<>("CastApi.API", zzbdk, zzf);
    }

    public zzbdj(Context context) {
        super(context, API, null, GoogleApi.zza.zzfsr);
    }

    public final Task<Void> zza(String[] strArr, String str, List<zzbl> list) {
        return zzb(new zzbdl(this, strArr, str, (List) null));
    }
}

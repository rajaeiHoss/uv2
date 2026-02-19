package com.google.android.gms.search;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcxy;
import com.google.android.gms.internal.zzcxz;

public class SearchAuth {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final SearchAuthApi SearchAuthApi = new zzcxz();
    private static Api.zzf<zzcxy> zzegu;
    private static final Api.zza<zzcxy, Api.ApiOptions.NoOptions> zzkld;

    public static class StatusCodes {
        public static final int AUTH_DISABLED = 10000;
        public static final int AUTH_THROTTLED = 10001;
        public static final int DEVELOPER_ERROR = 10;
        public static final int INTERNAL_ERROR = 8;
        public static final int SUCCESS = 0;
    }

    static {
        zzb zzb = new zzb();
        zzkld = zzb;
        Api.zzf<zzcxy> zzf = new Api.zzf<>();
        zzegu = zzf;
        API = new Api<>("SearchAuth.API", zzb, zzf);
    }

    private SearchAuth() {
    }
}

package com.google.android.gms.panorama;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcvn;
import com.google.android.gms.internal.zzcvu;

public final class Panorama {
    public static final Api<Api.ApiOptions.NoOptions> API;
    public static final PanoramaApi PanoramaApi = new zzcvn();
    public static final Api.zzf<zzcvu> zzegu;
    private static Api.zza<zzcvu, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzcvu> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzb zzb = new zzb();
        zzegv = zzb;
        API = new Api<>("Panorama.API", zzb, zzf);
    }

    private Panorama() {
    }
}

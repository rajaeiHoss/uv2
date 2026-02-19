package com.google.android.gms.safetynet;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcxf;
import com.google.android.gms.internal.zzcxs;
import com.google.android.gms.internal.zzcxt;

public final class SafetyNet {
    @Deprecated
    public static final Api<Api.ApiOptions.NoOptions> API;
    @Deprecated
    public static final SafetyNetApi SafetyNetApi = new zzcxf();
    private static Api.zzf<zzcxs> zzegu = new Api.zzf<>();
    private static Api.zza<zzcxs, Api.ApiOptions.NoOptions> zzegv;
    private static zzo zzkki = new zzcxt();

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.safetynet.zzo, com.google.android.gms.internal.zzcxt] */
    static {
        zzk zzk = new zzk();
        zzegv = zzk;
        API = new Api<>("SafetyNet.API", zzk, zzegu);
    }

    private SafetyNet() {
    }

    public static SafetyNetClient getClient(Activity activity) {
        return new SafetyNetClient(activity);
    }

    public static SafetyNetClient getClient(Context context) {
        return new SafetyNetClient(context);
    }
}

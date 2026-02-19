package com.google.android.gms.instantapps;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcdu;
import com.google.android.gms.internal.zzcej;
import com.google.android.gms.internal.zzcet;
import com.google.android.gms.internal.zzcex;
import com.google.android.gms.internal.zzcfa;

public final class InstantApps {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzcet> zzegu;
    private static final Api.zza<zzcet, Api.ApiOptions.NoOptions> zzegv;
    @Deprecated
    private static zzc zziof = new zzcej();

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.gms.internal.zzcej, com.google.android.gms.instantapps.zzc] */
    static {
        Api.zzf<zzcet> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzb zzb = new zzb();
        zzegv = zzb;
        API = new Api<>("InstantApps.API", zzb, zzf);
    }

    private InstantApps() {
    }

    public static ActivityCompat getActivityCompat(Activity activity) {
        return new zzcdu(activity);
    }

    public static InstantAppsClient getInstantAppsClient(Activity activity) {
        return new InstantAppsClient(activity);
    }

    public static InstantAppsClient getInstantAppsClient(Context context) {
        return new InstantAppsClient(context);
    }

    public static Launcher getLauncher(Context context) {
        return zzcex.zzdx(context);
    }

    public static PackageManagerCompat getPackageManagerCompat(Context context) {
        return zzcfa.zzf(context, true);
    }
}

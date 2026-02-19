package com.google.android.gms.phenotype;

import android.net.Uri;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.zzcvy;
import com.google.android.gms.internal.zzcvz;

public final class Phenotype {
    @Deprecated
    private static Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzcvz> zzegu;
    private static final Api.zza<zzcvz, Api.ApiOptions.NoOptions> zzegv;
    @Deprecated
    private static zzm zzkgr = new zzcvy();

    /* JADX WARNING: type inference failed for: r0v1, types: [com.google.android.gms.phenotype.zzm, com.google.android.gms.internal.zzcvy] */
    static {
        Api.zzf<zzcvz> zzf = new Api.zzf<>();
        zzegu = zzf;
        zzl zzl = new zzl();
        zzegv = zzl;
        API = new Api<>("Phenotype.API", zzl, zzf);
    }

    private Phenotype() {
    }

    public static Uri getContentProviderUri(String str) {
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? "content://com.google.android.gms.phenotype/".concat(valueOf) : new String("content://com.google.android.gms.phenotype/"));
    }
}

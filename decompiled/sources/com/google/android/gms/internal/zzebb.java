package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;

public final class zzebb {
    private static Api.zzf<zzeau> zzegu = new Api.zzf<>();
    private static final Api.zza<zzeau, zzebd> zzmre;
    public static final Api<zzebd> zzmrf;

    static {
        zzebc zzebc = new zzebc();
        zzmre = zzebc;
        zzmrf = new Api<>("InternalFirebaseAuth.FIREBASE_AUTH_API", zzebc, zzegu);
    }

    public static zzdzh zza(Context context, zzebd zzebd) {
        return new zzdzh(context, zzebd);
    }
}

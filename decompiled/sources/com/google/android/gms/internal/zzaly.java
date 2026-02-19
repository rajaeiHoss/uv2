package com.google.android.gms.internal;

import java.util.concurrent.Executor;

@zzabh
public final class zzaly {
    public static final Executor zzdjt;
    public static final Executor zzdju;
    private static zzalx zzdjv;
    private static zzalx zzdjw;

    static {
        zzalz zzalz = new zzalz();
        zzdjt = zzalz;
        zzama zzama = new zzama();
        zzdju = zzama;
        zzdjv = zza(zzalz);
        zzdjw = zza(zzama);
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.zzamb, com.google.android.gms.internal.zzalx] */
    private static zzalx zza(Executor executor) {
        return new zzamb(executor, (zzalz) null);
    }
}

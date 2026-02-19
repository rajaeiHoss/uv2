package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;

public final class zzz<O extends Api.ApiOptions> extends GoogleApi<O> {
    private final Api.zza<? extends zzcyj, zzcyk> zzfth;
    private final Api.zze zzfwd;
    private final zzt zzfwe;
    private final zzr zzfwf;

    public zzz(Context context, Api<O> api, Looper looper, Api.zze zze, zzt zzt, zzr zzr, Api.zza<? extends zzcyj, zzcyk> zza) {
        super(context, api, looper);
        this.zzfwd = zze;
        this.zzfwe = zzt;
        this.zzfwf = zzr;
        this.zzfth = zza;
        this.zzfsq.zza((GoogleApi<?>) this);
    }

    public final Api.zze zza(Looper looper, zzbo<O> zzbo) {
        this.zzfwe.zza(zzbo);
        return this.zzfwd;
    }

    public final zzcv zza(Context context, Handler handler) {
        return new zzcv(context, handler, this.zzfwf, this.zzfth);
    }

    public final Api.zze zzaix() {
        return this.zzfwd;
    }
}

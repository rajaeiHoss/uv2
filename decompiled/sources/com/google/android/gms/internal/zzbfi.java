package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzg;

public final class zzbfi extends GoogleApi<Api.ApiOptions.NoOptions> implements zzb {
    private zzbfi(Context context) {
        super(context, ClearcutLogger.API, null, (zzda) new zzg());
    }

    public static zzb zzcb(Context context) {
        return new zzbfi(context);
    }

    public final PendingResult<Status> zza(zze zze) {
        return zzc(new zzbfl(zze, zzahw()));
    }
}

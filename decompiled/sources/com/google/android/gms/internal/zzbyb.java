package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbyb extends zzbwq<zzbzi> {
    private static Api.zzf<zzbyb> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.SESSIONS_API", new zzbyd(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.SESSIONS_CLIENT", new zzbyf(), zzegu);

    zzbyb(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 58, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbzi zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
        return queryLocalInterface instanceof zzbzi ? (zzbzi) queryLocalInterface : new zzbzj(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.SessionsApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi";
    }
}

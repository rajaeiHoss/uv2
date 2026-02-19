package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbxj extends zzbwq<zzbzc> {
    private static Api.zzf<zzbxj> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.API", new zzbxl(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.CLIENT", new zzbxn(), zzegu);

    zzbxj(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 57, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbzc zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitHistoryApi");
        return queryLocalInterface instanceof zzbzc ? (zzbzc) queryLocalInterface : new zzbzd(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.HistoryApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitHistoryApi";
    }
}

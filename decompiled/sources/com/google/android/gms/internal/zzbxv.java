package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbxv extends zzbwq<zzbzg> {
    private static Api.zzf<zzbxv> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.SENSORS_API", new zzbxx(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.SENSORS_CLIENT", new zzbxz(), zzegu);

    zzbxv(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 55, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbzg zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
        return queryLocalInterface instanceof zzbzg ? (zzbzg) queryLocalInterface : new zzbzh(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.SensorsApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi";
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbwy extends zzbwq<zzbyy> {
    private static Api.zzf<zzbwy> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.CONFIG_API", new zzbxa(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.CONFIG_CLIENT", new zzbxc(), zzegu);

    zzbwy(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 60, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbyy zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
        return queryLocalInterface instanceof zzbyy ? (zzbyy) queryLocalInterface : new zzbyz(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.ConfigApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitConfigApi";
    }
}

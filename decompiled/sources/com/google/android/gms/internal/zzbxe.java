package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbxe extends zzbwq<zzbza> {
    private static Api.zzf<zzbxe> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.GOALS_API", new zzbxg(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.GOALS_CLIENT", new zzbxi(), zzegu);

    zzbxe(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 125, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbza zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitGoalsApi");
        return queryLocalInterface instanceof zzbza ? (zzbza) queryLocalInterface : new zzbzb(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.GoalsApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitGoalsApi";
    }
}

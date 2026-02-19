package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbws extends zzbwq<zzbyw> {
    private static Api.zzf<zzbws> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.BLE_API", new zzbwu(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.BLE_CLIENT", new zzbww(), zzegu);

    zzbws(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 59, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbyw zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitBleApi");
        return queryLocalInterface instanceof zzbyw ? (zzbyw) queryLocalInterface : new zzbyx(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.BleApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitBleApi";
    }
}

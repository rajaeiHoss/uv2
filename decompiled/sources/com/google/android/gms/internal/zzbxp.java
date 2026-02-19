package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.fitness.FitnessOptions;

public final class zzbxp extends zzbwq<zzbze> {
    private static Api.zzf<zzbxp> zzegu = new Api.zzf<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.RECORDING_API", new zzbxr(), zzegu);
    public static final Api<FitnessOptions> zzhmg = new Api<>("Fitness.RECORDING_CLIENT", new zzbxt(), zzegu);

    zzbxp(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 56, connectionCallbacks, onConnectionFailedListener, zzr);
    }

    public final zzbze zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
        return queryLocalInterface instanceof zzbze ? (zzbze) queryLocalInterface : new zzbzf(iBinder);
    }

    public final String zzhm() {
        return "com.google.android.gms.fitness.RecordingApi";
    }

    public final String zzhn() {
        return "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi";
    }
}

package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zzc;
import com.google.android.gms.panorama.zza;

public final class zzcvu extends zzab<zzcvl> {
    public zzcvu(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 3, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    public final zzc[] zzalu() {
        return new zzc[]{zza.zzkfe};
    }

    public final /* synthetic */ zzcvl zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.panorama.internal.IPanoramaService");
        return queryLocalInterface instanceof zzcvl ? (zzcvl) queryLocalInterface : new zzcvm(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.panorama.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
}

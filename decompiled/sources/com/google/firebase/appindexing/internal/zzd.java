package com.google.firebase.appindexing.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzd extends zzab<zzu> {
    static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.zzf<zzd> zzegu;
    private static final Api.zza<zzd, Api.ApiOptions.NoOptions> zzegv;

    static {
        Api.zzf<zzd> zzf = new Api.zzf<>();
        zzegu = zzf;
        zze zze = new zze();
        zzegv = zze;
        API = new Api<>("AppIndexing.API", zze, zzf);
    }

    public zzd(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 113, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    public final zzu zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.appindexing.internal.IAppIndexingService");
        return queryLocalInterface instanceof zzu ? (zzu) queryLocalInterface : new zzv(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.icing.APP_INDEXING_SERVICE";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.firebase.appindexing.internal.IAppIndexingService";
    }
}

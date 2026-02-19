package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public class zzcfq extends zzab<zzcgw> {
    private final String zzitj;
    protected final zzchr<zzcgw> zzitk = new zzcfr(this);

    public zzcfq(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, String str, zzr zzr) {
        super(context, looper, 23, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzitj = str;
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzitj);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzcgw zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzcgw ? (zzcgw) queryLocalInterface : new zzcgx(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
}

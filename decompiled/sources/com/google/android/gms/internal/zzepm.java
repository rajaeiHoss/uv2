package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzepm extends zzab<zzepv> {
    public zzepm(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 131, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzepv zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.firebase.dynamiclinks.internal.IDynamicLinksService");
        return queryLocalInterface instanceof zzepv ? (zzepv) queryLocalInterface : new zzepw(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.firebase.dynamiclinks.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.firebase.dynamiclinks.internal.IDynamicLinksService";
    }
}

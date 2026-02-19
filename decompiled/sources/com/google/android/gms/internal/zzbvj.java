package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzbvj extends zzab<zzbvn> {
    public zzbvj(Context context, Looper looper, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 149, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        bundle.putString("FIDO2_ACTION_START_SERVICE", "com.google.android.gms.fido.fido2.privileged.START");
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzbvn zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedService");
        return queryLocalInterface instanceof zzbvn ? (zzbvn) queryLocalInterface : new zzbvo(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.fido.fido2.privileged.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedService";
    }
}

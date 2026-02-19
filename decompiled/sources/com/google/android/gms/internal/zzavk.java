package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzavk extends zzab<zzavo> {
    private final String zzehh;

    public zzavk(Context context, Looper looper, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzr zzr) {
        super(context, looper, 77, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzehh = zzr.zzami();
    }

    public final void zza(zzavm zzavm) {
        try {
            ((zzavo) zzalw()).zza(zzavm);
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Bundle bundle = new Bundle();
        bundle.putString("authPackage", this.zzehh);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final zzavo zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.appinvite.internal.IAppInviteService");
        return queryLocalInterface instanceof zzavo ? (zzavo) queryLocalInterface : new zzavp(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.appinvite.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.appinvite.internal.IAppInviteService";
    }
}

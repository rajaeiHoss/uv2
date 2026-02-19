package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzaxi extends zzab<zzaxn> {
    private final Auth.AuthCredentialsOptions zzelw;

    public zzaxi(Context context, Looper looper, zzr zzr, Auth.AuthCredentialsOptions authCredentialsOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 68, zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzelw = authCredentialsOptions;
    }

    /* access modifiers changed from: protected */
    public final Bundle zzabt() {
        Auth.AuthCredentialsOptions authCredentialsOptions = this.zzelw;
        return authCredentialsOptions == null ? new Bundle() : authCredentialsOptions.toBundle();
    }

    /* access modifiers changed from: package-private */
    public final Auth.AuthCredentialsOptions zzacb() {
        return this.zzelw;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ zzaxn zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        return queryLocalInterface instanceof zzaxn ? (zzaxn) queryLocalInterface : new zzaxo(iBinder);
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }
}

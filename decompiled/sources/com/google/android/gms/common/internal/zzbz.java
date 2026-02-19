package com.google.android.gms.common.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zzbz<T extends IInterface> extends zzab<T> {
    public zzbz(Context context, Looper looper, int i, zzr zzr, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, i, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    public static Api.zzg<?> zzanb() {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: protected */
    public final void zzb(int i, T t) {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: protected */
    public final T zzd(IBinder iBinder) {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: protected */
    public final String zzhm() {
        throw new NoSuchMethodError();
    }

    /* access modifiers changed from: protected */
    public final String zzhn() {
        throw new NoSuchMethodError();
    }
}

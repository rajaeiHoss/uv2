package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.util.zzj;
import com.google.android.gms.fitness.zzg;
import java.util.Set;

public abstract class zzbwq<T extends IInterface> extends zzab<T> {
    protected zzbwq(Context context, Looper looper, int i, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, zzr zzr) {
        super(context, looper, i, zzr, connectionCallbacks, onConnectionFailedListener);
    }

    public final boolean zzacc() {
        return !zzj.zzcu(getContext());
    }

    public final boolean zzalx() {
        return true;
    }

    /* access modifiers changed from: protected */
    public final Set<Scope> zzb(Set<Scope> set) {
        return zzg.zzi(set);
    }

    public abstract T zzd(IBinder iBinder);

    public abstract String zzhm();

    public abstract String zzhn();
}

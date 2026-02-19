package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

public abstract class zzbct<R extends Result> extends zzbdf<R> {
    protected zzben zzezb;

    public zzbct(zzbcl zzbcl) {
        super(zzbcl.zzffo);
    }

    public abstract void execute();

    /* access modifiers changed from: protected */
    public final void zza(zzbdp zzbdp) throws RemoteException {
        execute();
    }
}

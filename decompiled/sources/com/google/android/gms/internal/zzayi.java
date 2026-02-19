package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzayi extends zzayg {
    private /* synthetic */ ProxyRequest zzemd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzayi(zzayh zzayh, GoogleApiClient googleApiClient, ProxyRequest proxyRequest) {
        super(googleApiClient);
        this.zzemd = proxyRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzaxv zzaxv) throws RemoteException {
        zzaxv.zza(new zzayj(this), this.zzemd);
    }
}

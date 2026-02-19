package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.request.StartBleScanRequest;

final class zzbzx extends zzbwx {
    private /* synthetic */ StartBleScanRequest zzhmh;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbzx(zzbzw zzbzw, GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest) {
        super(googleApiClient);
        this.zzhmh = startBleScanRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbws zzbws) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new StartBleScanRequest(this.zzhmh, (zzbzt) new zzcbq(this)));
    }
}

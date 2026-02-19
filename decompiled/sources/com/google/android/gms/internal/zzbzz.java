package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.zze;

final class zzbzz extends zzbwx {
    private /* synthetic */ String zzhmj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbzz(zzbzw zzbzw, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzhmj = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbws zzbws) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new zze(this.zzhmj, (BleDevice) null, (zzbzt) new zzcbq(this)));
    }
}

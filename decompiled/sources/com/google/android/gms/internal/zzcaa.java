package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.zze;

final class zzcaa extends zzbwx {
    private /* synthetic */ BleDevice zzhmk;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcaa(zzbzw zzbzw, GoogleApiClient googleApiClient, BleDevice bleDevice) {
        super(googleApiClient);
        this.zzhmk = bleDevice;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbws zzbws) throws RemoteException {
        ((zzbyw) zzbws.zzalw()).zza(new zze(this.zzhmk.getAddress(), this.zzhmk, (zzbzt) new zzcbq(this)));
    }
}

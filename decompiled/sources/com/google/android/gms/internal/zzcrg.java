package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;

final class zzcrg extends zzcru {
    private /* synthetic */ String zzjyt;
    private /* synthetic */ byte[] zzjza;
    private /* synthetic */ zzci zzjzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrg(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, byte[] bArr, zzci zzci) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
        this.zzjza = bArr;
        this.zzjzc = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        ((zzcso) zzcov.zzalw()).zza(new zzcop(new zzcpw(this).asBinder(), new zzcpp(this.zzjzc).asBinder(), this.zzjyt, this.zzjza, (IBinder) null));
    }
}

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;

final class zzcrf extends zzcru {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzjyt;
    private /* synthetic */ byte[] zzjza;
    private /* synthetic */ zzci zzjzb;
    private /* synthetic */ zzci zzjzc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrf(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, String str2, byte[] bArr, zzci zzci, zzci zzci2) {
        super(googleApiClient, (zzcqx) null);
        this.val$name = str;
        this.zzjyt = str2;
        this.zzjza = bArr;
        this.zzjzb = zzci;
        this.zzjzc = zzci2;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        String str = this.val$name;
        String str2 = this.zzjyt;
        byte[] bArr = this.zzjza;
        zzci zzci = this.zzjzb;
        zzci zzci2 = this.zzjzc;
        ((zzcso) zzcov.zzalw()).zza(new zzcug(new zzcpw(this).asBinder(), new zzcpp(zzci2).asBinder(), new zzcpg(zzci).asBinder(), str, str2, bArr, (IBinder) null));
    }
}

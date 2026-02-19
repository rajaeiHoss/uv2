package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.Strategy;

final class zzcrd extends zzcrs {
    private /* synthetic */ String val$name;
    private /* synthetic */ long zzjyx;
    private /* synthetic */ zzci zzjyy;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrd(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, long j, zzci zzci) {
        super(googleApiClient, (zzcqx) null);
        this.val$name = str;
        this.zzjyx = j;
        this.zzjyy = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        String str = this.val$name;
        long j = this.zzjyx;
        zzci zzci = this.zzjyy;
        ((zzcso) zzcov.zzalw()).zza(new zzcuk(new zzcpy(this).asBinder(), new zzcpe(zzci).asBinder(), str, "__LEGACY_SERVICE_ID__", j, new AdvertisingOptions.Builder().setStrategy(Strategy.P2P_CLUSTER).build(), (IBinder) null));
    }
}

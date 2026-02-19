package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.Strategy;

final class zzcre extends zzcru {
    private /* synthetic */ String zzjym;
    private /* synthetic */ long zzjyx;
    private /* synthetic */ zzci zzjyz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcre(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, long j, zzci zzci) {
        super(googleApiClient, (zzcqx) null);
        this.zzjym = str;
        this.zzjyx = j;
        this.zzjyz = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        String str = this.zzjym;
        long j = this.zzjyx;
        zzci zzci = this.zzjyz;
        ((zzcso) zzcov.zzalw()).zza(new zzcum(new zzcpw(this).asBinder(), (IBinder) null, str, j, new DiscoveryOptions.Builder().setStrategy(Strategy.P2P_CLUSTER).build(), new zzcpm(zzci).asBinder()));
    }
}

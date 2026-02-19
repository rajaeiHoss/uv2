package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;

final class zzcrm extends zzcru {
    private /* synthetic */ String zzjym;
    private /* synthetic */ DiscoveryOptions zzjyp;
    private /* synthetic */ zzci zzjzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrm(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, zzci zzci, DiscoveryOptions discoveryOptions) {
        super(googleApiClient, (zzcqx) null);
        this.zzjym = str;
        this.zzjzf = zzci;
        this.zzjyp = discoveryOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, this.zzjym, (zzci<EndpointDiscoveryCallback>) this.zzjzf, this.zzjyp);
    }
}

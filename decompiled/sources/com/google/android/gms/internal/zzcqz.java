package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;
import java.util.List;

final class zzcqz extends zzcru {
    private /* synthetic */ Payload zzjyu;
    private /* synthetic */ List zzjyv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcqz(zzcqw zzcqw, GoogleApiClient googleApiClient, List list, Payload payload) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyv = list;
        this.zzjyu = payload;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, (String[]) this.zzjyv.toArray(new String[0]), this.zzjyu, false);
    }
}

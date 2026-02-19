package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;

final class zzcqy extends zzcru {
    private /* synthetic */ String zzjyt;
    private /* synthetic */ Payload zzjyu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcqy(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, Payload payload) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
        this.zzjyu = payload;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, new String[]{this.zzjyt}, this.zzjyu, false);
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;

final class zzcri extends zzcru {
    private /* synthetic */ String zzjyt;
    private /* synthetic */ byte[] zzjzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcri(zzcqw zzcqw, GoogleApiClient googleApiClient, String str, byte[] bArr) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyt = str;
        this.zzjzd = bArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, new String[]{this.zzjyt}, Payload.fromBytes(this.zzjzd), true);
    }
}

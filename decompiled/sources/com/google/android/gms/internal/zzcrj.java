package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;
import java.util.List;

final class zzcrj extends zzcru {
    private /* synthetic */ List zzjyv;
    private /* synthetic */ byte[] zzjzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcrj(zzcqw zzcqw, GoogleApiClient googleApiClient, List list, byte[] bArr) {
        super(googleApiClient, (zzcqx) null);
        this.zzjyv = list;
        this.zzjzd = bArr;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzcov zzcov) throws RemoteException {
        zzcov.zza((zzn<Status>) this, (String[]) this.zzjyv.toArray(new String[0]), Payload.fromBytes(this.zzjzd), true);
    }
}

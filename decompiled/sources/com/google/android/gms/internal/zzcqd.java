package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;

final /* synthetic */ class zzcqd implements zzcqs {
    private final String zzdiu;
    private final Payload zzjyh;

    zzcqd(String str, Payload payload) {
        this.zzdiu = str;
        this.zzjyh = payload;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zza((zzn<Status>) zzn, new String[]{this.zzdiu}, this.zzjyh, false);
    }
}

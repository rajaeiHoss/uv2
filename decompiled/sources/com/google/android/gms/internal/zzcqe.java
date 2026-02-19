package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.Payload;
import java.util.List;

final /* synthetic */ class zzcqe implements zzcqs {
    private final Payload zzjyh;
    private final List zzjyi;

    zzcqe(List list, Payload payload) {
        this.zzjyi = list;
        this.zzjyh = payload;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zza((zzn<Status>) zzn, (String[]) this.zzjyi.toArray(new String[0]), this.zzjyh, false);
    }
}

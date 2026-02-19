package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;

final /* synthetic */ class zzcqa implements zzcqs {
    private final String zzaqy;
    private final String zzdiu;
    private final zzci zzjyf;

    zzcqa(String str, String str2, zzci zzci) {
        this.zzdiu = str;
        this.zzaqy = str2;
        this.zzjyf = zzci;
    }

    public final void zza(zzcov zzcov, zzn zzn) throws RemoteException {
        zzcov.zza((zzn<Status>) zzn, this.zzdiu, this.zzaqy, (zzci<ConnectionLifecycleCallback>) this.zzjyf);
    }
}

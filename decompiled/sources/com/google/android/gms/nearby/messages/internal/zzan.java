package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final /* synthetic */ class zzan implements zzbd {
    private final zzci zzjyg;
    private final zzak zzkdf;
    private final zzbg zzkdk;
    private final SubscribeOptions zzkdl;

    zzan(zzak zzak, zzci zzci, zzbg zzbg, SubscribeOptions subscribeOptions) {
        this.zzkdf = zzak;
        this.zzjyg = zzci;
        this.zzkdk = zzbg;
        this.zzkdl = subscribeOptions;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        this.zzkdf.zza(this.zzjyg, this.zzkdk, this.zzkdl, zzah, zzci);
    }
}

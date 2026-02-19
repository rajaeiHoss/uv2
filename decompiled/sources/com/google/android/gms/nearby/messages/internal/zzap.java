package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import android.app.PendingIntent;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final /* synthetic */ class zzap implements zzbd {
    private final zzak zzkdf;
    private final zzbg zzkdk;
    private final SubscribeOptions zzkdl;
    private final PendingIntent zzkdm;

    zzap(zzak zzak, PendingIntent pendingIntent, zzbg zzbg, SubscribeOptions subscribeOptions) {
        this.zzkdf = zzak;
        this.zzkdm = pendingIntent;
        this.zzkdk = zzbg;
        this.zzkdl = subscribeOptions;
    }

    public final void zza(zzah zzah, zzci zzci) throws RemoteException {
        this.zzkdf.zza(this.zzkdm, this.zzkdk, this.zzkdl, zzah, zzci);
    }
}

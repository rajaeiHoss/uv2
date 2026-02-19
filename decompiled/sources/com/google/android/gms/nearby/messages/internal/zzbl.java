package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.PublishOptions;

final class zzbl extends zzbv {
    private /* synthetic */ Message zzkdx;
    private /* synthetic */ zzbt zzkdy;
    private /* synthetic */ PublishOptions zzkdz;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbl(zzbi zzbi, GoogleApiClient googleApiClient, Message message, zzbt zzbt, PublishOptions publishOptions) {
        super(googleApiClient);
        this.zzkdx = message;
        this.zzkdy = zzbt;
        this.zzkdz = publishOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), zzaf.zza(this.zzkdx), (zzv) this.zzkdy, this.zzkdz);
    }
}

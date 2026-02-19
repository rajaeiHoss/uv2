package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.SubscribeOptions;

final class zzbn extends zzbv {
    private /* synthetic */ zzci zzhsp;
    private /* synthetic */ zzbw zzkea;
    private /* synthetic */ SubscribeOptions zzkeb;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbn(zzbi zzbi, GoogleApiClient googleApiClient, zzci zzci, zzbw zzbw, SubscribeOptions subscribeOptions) {
        super(googleApiClient);
        this.zzhsp = zzci;
        this.zzkea = zzbw;
        this.zzkeb = subscribeOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), (zzci<MessageListener>) this.zzhsp, (zzab) this.zzkea, this.zzkeb, (byte[]) null);
    }
}

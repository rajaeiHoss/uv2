package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.nearby.messages.MessageListener;

final class zzbp extends zzbv {
    private /* synthetic */ zzci zzhsp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbp(zzbi zzbi, GoogleApiClient googleApiClient, zzci zzci) {
        super(googleApiClient);
        this.zzhsp = zzci;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), (zzci<MessageListener>) this.zzhsp);
    }
}

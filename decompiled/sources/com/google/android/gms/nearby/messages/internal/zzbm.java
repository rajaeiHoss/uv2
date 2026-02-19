package com.google.android.gms.nearby.messages.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.nearby.messages.Message;

final class zzbm extends zzbv {
    private /* synthetic */ Message zzkdx;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbm(zzbi zzbi, GoogleApiClient googleApiClient, Message message) {
        super(googleApiClient);
        this.zzkdx = message;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzah zzah) throws RemoteException {
        zzah.zza(zzbdx(), zzaf.zza(this.zzkdx));
    }
}

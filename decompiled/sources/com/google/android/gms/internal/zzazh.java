package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import java.util.ArrayList;

final class zzazh extends zzbld {
    private /* synthetic */ int zzeru;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzazh(GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
        this.zzeru = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzblg zzblg) throws RemoteException {
        zzblg.zza((zzn<zzazy>) this, new zzazw(this.zzeru, (ArrayList<zzayq>) null));
    }
}

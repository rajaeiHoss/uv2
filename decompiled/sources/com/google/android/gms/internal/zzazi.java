package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.games.GamesActivityResultCodes;
import java.util.ArrayList;

final class zzazi extends zzbld {
    private /* synthetic */ int zzeru = GamesActivityResultCodes.RESULT_LICENSE_FAILED;
    private /* synthetic */ ArrayList zzerv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzazi(GoogleApiClient googleApiClient, int i, ArrayList arrayList) {
        super(googleApiClient);
        this.zzerv = arrayList;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzblg zzblg) throws RemoteException {
        zzblg.zza((zzn<zzazy>) this, new zzazw(this.zzeru, this.zzerv));
    }
}

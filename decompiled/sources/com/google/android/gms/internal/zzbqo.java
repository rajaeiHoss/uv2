package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.List;

final class zzbqo extends zzbnp {
    private /* synthetic */ List zzgvt;
    private /* synthetic */ zzbql zzgwm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbqo(zzbql zzbql, GoogleApiClient googleApiClient, List list) {
        super(googleApiClient);
        this.zzgwm = zzbql;
        this.zzgvt = list;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtm(this.zzgwm.zzgpe, this.zzgvt), (zzbrm) new zzbto(this));
    }
}

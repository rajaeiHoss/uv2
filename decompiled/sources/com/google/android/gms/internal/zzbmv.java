package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.query.Query;

final class zzbmv extends zzbni {
    private /* synthetic */ Query zzgtm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbmv(zzbmu zzbmu, GoogleApiClient googleApiClient, Query query) {
        super(googleApiClient);
        this.zzgtm = query;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtg(this.zzgtm), (zzbrm) new zzbnj(this));
    }
}

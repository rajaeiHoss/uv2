package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbn;

final class zzcba extends zzbxu {
    private /* synthetic */ DataType zzhmw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcba(zzcaw zzcaw, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzhmw = dataType;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxp zzbxp) throws RemoteException {
        ((zzbze) zzbxp.zzalw()).zza(new zzbn(this.zzhmw, (DataSource) null, (zzbzt) new zzcbq(this)));
    }
}

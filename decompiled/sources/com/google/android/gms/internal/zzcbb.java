package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzbn;

final class zzcbb extends zzbxu {
    private /* synthetic */ DataSource zzhnc;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcbb(zzcaw zzcaw, GoogleApiClient googleApiClient, DataSource dataSource) {
        super(googleApiClient);
        this.zzhnc = dataSource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxp zzbxp) throws RemoteException {
        ((zzbze) zzbxp.zzalw()).zza(new zzbn((DataType) null, this.zzhnc, (zzbzt) new zzcbq(this)));
    }
}

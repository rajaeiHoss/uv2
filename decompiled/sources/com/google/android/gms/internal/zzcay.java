package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzaj;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final class zzcay extends zzbxs<ListSubscriptionsResult> {
    private /* synthetic */ DataType zzhmw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcay(zzcaw zzcaw, GoogleApiClient googleApiClient, DataType dataType) {
        super(googleApiClient);
        this.zzhmw = dataType;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxp zzbxp) throws RemoteException {
        ((zzbze) zzbxp.zzalw()).zza(new zzaj(this.zzhmw, (zzbzk) new zzcbc(this, (zzcax) null)));
    }

    /* access modifiers changed from: protected */
    public final ListSubscriptionsResult zzb(Status status) {
        return ListSubscriptionsResult.zzag(status);
    }
}

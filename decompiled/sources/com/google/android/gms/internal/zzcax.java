package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.zzaj;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

final class zzcax extends zzbxs<ListSubscriptionsResult> {
    zzcax(zzcaw zzcaw, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbxp zzbxp) throws RemoteException {
        ((zzbze) zzbxp.zzalw()).zza(new zzaj((DataType) null, (zzbzk) new zzcbc(this, (zzcax) null)));
    }

    /* access modifiers changed from: protected */
    public final ListSubscriptionsResult zzb(Status status) {
        return ListSubscriptionsResult.zzag(status);
    }
}

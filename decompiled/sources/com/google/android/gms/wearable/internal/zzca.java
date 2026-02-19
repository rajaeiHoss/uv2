package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.wearable.DataItemBuffer;

final class zzca extends zzn<DataItemBuffer> {
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ int zzltv;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzca(zzbw zzbw, GoogleApiClient googleApiClient, Uri uri, int i) {
        super(googleApiClient);
        this.zzkff = uri;
        this.zzltv = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza((zzek) new zzgw(this), this.zzkff, this.zzltv);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ DataItemBuffer zzb(Status status) {
        return new DataItemBuffer(DataHolder.zzbz(status.getStatusCode()));
    }
}

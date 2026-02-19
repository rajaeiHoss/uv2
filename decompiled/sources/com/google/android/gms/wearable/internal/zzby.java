package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataItem;

final class zzby extends zzn<DataApi.DataItemResult> {
    private /* synthetic */ Uri zzkff;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzby(zzbw zzbw, GoogleApiClient googleApiClient, Uri uri) {
        super(googleApiClient);
        this.zzkff = uri;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza((zzek) new zzgv(this), this.zzkff);
    }

    /* access modifiers changed from: protected */
    public final DataApi.DataItemResult zzb(Status status) {
        return new zzcg(status, (DataItem) null);
    }
}

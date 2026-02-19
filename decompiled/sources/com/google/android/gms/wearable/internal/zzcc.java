package com.google.android.gms.wearable.internal;

import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;

final class zzcc extends zzn<DataApi.GetFdForAssetResult> {
    private /* synthetic */ Asset zzltw;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzcc(zzbw zzbw, GoogleApiClient googleApiClient, Asset asset) {
        super(googleApiClient);
        this.zzltw = asset;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<DataApi.GetFdForAssetResult>) this, this.zzltw);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ DataApi.GetFdForAssetResult zzb(Status status) {
        return new zzci(status, (ParcelFileDescriptor) null);
    }
}

package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;

final class zzs extends zzn<CapabilityApi.RemoveLocalCapabilityResult> {
    private /* synthetic */ String zzlsm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzs(zzo zzo, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzlsm = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zzb(new zzhd(this), this.zzlsm);
    }

    /* access modifiers changed from: protected */
    public final CapabilityApi.RemoveLocalCapabilityResult zzb(Status status) {
        return new zzu(status);
    }
}

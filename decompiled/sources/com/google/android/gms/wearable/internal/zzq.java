package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import java.util.Map;

final class zzq extends zzn<CapabilityApi.GetAllCapabilitiesResult> {
    private /* synthetic */ int zzlsn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzq(zzo zzo, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
        this.zzlsn = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza((zzek) new zzgq(this), this.zzlsn);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ CapabilityApi.GetAllCapabilitiesResult zzb(Status status) {
        return new zzx(status, (Map<String, CapabilityInfo>) null);
    }
}

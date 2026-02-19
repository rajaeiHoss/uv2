package com.google.android.gms.wearable.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzp extends zzn<CapabilityApi.GetCapabilityResult> {
    private /* synthetic */ String zzlsm;
    private /* synthetic */ int zzlsn;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzp(zzo zzo, GoogleApiClient googleApiClient, String str, int i) {
        super(googleApiClient);
        this.zzlsm = str;
        this.zzlsn = i;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        ((zzep) zzhg.zzalw()).zza((zzek) new zzgr(this), this.zzlsm, this.zzlsn);
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ CapabilityApi.GetCapabilityResult zzb(Status status) {
        return new zzy(status, (CapabilityInfo) null);
    }
}

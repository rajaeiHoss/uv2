package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.zzm;
import java.util.Arrays;
import java.util.List;

final class zzl extends zzm.zzc<zzo> {
    private /* synthetic */ String[] zzixg;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzl(zzh zzh, Api api, GoogleApiClient googleApiClient, String[] strArr) {
        super(api, googleApiClient);
        this.zzixg = strArr;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzo zzo) throws RemoteException {
        zzo.zza(new zzm((zzm.zzc) this), (List<String>) Arrays.asList(this.zzixg));
    }
}

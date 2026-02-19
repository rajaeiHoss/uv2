package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzbd extends zzn<Status> {
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ zzay zzlti;
    private /* synthetic */ boolean zzltj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbd(zzay zzay, GoogleApiClient googleApiClient, Uri uri, boolean z) {
        super(googleApiClient);
        this.zzlti = zzay;
        this.zzkff = uri;
        this.zzltj = z;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<Status>) this, this.zzlti.zzeia, this.zzkff, this.zzltj);
    }

    public final /* synthetic */ Status zzb(Status status) {
        return status;
    }
}

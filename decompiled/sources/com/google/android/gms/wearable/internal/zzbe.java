package com.google.android.gms.wearable.internal;

import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzbe extends zzn<Status> {
    private /* synthetic */ Uri zzkff;
    private /* synthetic */ zzay zzlti;
    private /* synthetic */ long zzltk;
    private /* synthetic */ long zzltl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbe(zzay zzay, GoogleApiClient googleApiClient, Uri uri, long j, long j2) {
        super(googleApiClient);
        this.zzlti = zzay;
        this.zzkff = uri;
        this.zzltk = j;
        this.zzltl = j2;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void zza(zzhg zzhg) throws RemoteException {
        zzhg.zza((com.google.android.gms.common.api.internal.zzn<Status>) this, this.zzlti.zzeia, this.zzkff, this.zzltk, this.zzltl);
    }

    public final /* synthetic */ Status zzb(Status status) {
        return status;
    }
}

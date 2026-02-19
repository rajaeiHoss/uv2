package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzavr extends zzm<Result, zzawa> {
    private /* synthetic */ boolean zzeih;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzavr(zzavq zzavq, Api api, GoogleApiClient googleApiClient, boolean z) {
        super((Api<?>) api, googleApiClient);
        this.zzeih = z;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawa zzawa) throws RemoteException {
        ((zzc) zzawa.zzalw()).zzau(this.zzeih);
        setResult(new zzavy(Status.zzftq));
    }

    /* access modifiers changed from: protected */
    public final Result zzb(Status status) {
        return new zzavy(status);
    }
}

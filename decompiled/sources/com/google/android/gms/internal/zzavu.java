package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzavu extends zzm<Result, zzawa> {
    private /* synthetic */ Account zzehs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzavu(zzavq zzavq, Api api, GoogleApiClient googleApiClient, Account account) {
        super((Api<?>) api, googleApiClient);
        this.zzehs = account;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawa zzawa) throws RemoteException {
        ((zzc) zzawa.zzalw()).zza(new zzavv(this), this.zzehs);
    }

    /* access modifiers changed from: protected */
    public final Result zzb(Status status) {
        return new zzavz(status);
    }
}

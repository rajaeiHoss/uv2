package com.google.android.gms.internal;

import android.accounts.Account;
import android.os.RemoteException;
import com.google.android.gms.auth.account.WorkAccountApi;
import com.google.android.gms.auth.account.zzc;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzavs extends zzm<WorkAccountApi.AddAccountResult, zzawa> {
    private /* synthetic */ String zzehu;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzavs(zzavq zzavq, Api api, GoogleApiClient googleApiClient, String str) {
        super((Api<?>) api, googleApiClient);
        this.zzehu = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzawa zzawa) throws RemoteException {
        ((zzc) zzawa.zzalw()).zza(new zzavt(this), this.zzehu);
    }

    /* access modifiers changed from: protected */
    public final WorkAccountApi.AddAccountResult zzb(Status status) {
        return new zzavx(status, (Account) null);
    }
}

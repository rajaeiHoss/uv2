package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzg extends zzm<GoogleSignInResult> {
    final /* synthetic */ Context val$context;
    final /* synthetic */ GoogleSignInOptions zzenq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzg(GoogleApiClient googleApiClient, Context context, GoogleSignInOptions googleSignInOptions) {
        super(googleApiClient);
        this.val$context = context;
        this.zzenq = googleSignInOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zze zze) throws RemoteException {
        ((zzv) zze.zzalw()).zza(new zzh(this), this.zzenq);
    }

    /* access modifiers changed from: protected */
    public final GoogleSignInResult zzb(Status status) {
        return new GoogleSignInResult((GoogleSignInAccount) null, status);
    }
}

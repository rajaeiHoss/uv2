package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzaxa extends zzaxg<CredentialRequestResult> {
    private /* synthetic */ CredentialRequest zzels;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaxa(zzawz zzawz, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        super(googleApiClient);
        this.zzels = credentialRequest;
    }

    /* access modifiers changed from: protected */
    public final void zza(Context context, zzaxn zzaxn) throws RemoteException {
        zzaxn.zza((zzaxl) new zzaxb(this), this.zzels);
    }

    /* access modifiers changed from: protected */
    public final CredentialRequestResult zzb(Status status) {
        return zzawy.zzf(status);
    }
}

package com.google.android.gms.fido.fido2;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.internal.zzbvj;
import com.google.android.gms.internal.zzbvl;
import com.google.android.gms.internal.zzbvn;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzg extends zzde<zzbvj, Fido2PendingIntent> {
    private /* synthetic */ BrowserPublicKeyCredentialRequestOptions zzheh;

    zzg(Fido2PrivilegedApiClient fido2PrivilegedApiClient, BrowserPublicKeyCredentialRequestOptions browserPublicKeyCredentialRequestOptions) {
        this.zzheh = browserPublicKeyCredentialRequestOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbvj zzbvj, TaskCompletionSource<Fido2PendingIntent> taskCompletionSource) throws RemoteException {
        ((zzbvn) zzbvj.zzalw()).zza((zzbvl) new zzh(this, taskCompletionSource), this.zzheh);
    }
}

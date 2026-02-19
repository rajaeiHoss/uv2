package com.google.android.gms.fido.fido2;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.fido.fido2.api.common.MakeCredentialOptions;
import com.google.android.gms.internal.zzbvp;
import com.google.android.gms.internal.zzbvr;
import com.google.android.gms.internal.zzbvt;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zza extends zzde<zzbvp, Fido2PendingIntent> {
    private /* synthetic */ MakeCredentialOptions zzheb;

    zza(Fido2ApiClient fido2ApiClient, MakeCredentialOptions makeCredentialOptions) {
        this.zzheb = makeCredentialOptions;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbvp zzbvp, TaskCompletionSource<Fido2PendingIntent> taskCompletionSource) throws RemoteException {
        ((zzbvt) zzbvp.zzalw()).zza((zzbvr) new zzb(this, taskCompletionSource), this.zzheb);
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

final class zzave extends zzavd<Status> {
    private final String zzeha;

    public zzave(zzavb zzavb, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzeha = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzavk zzavk) throws RemoteException {
        try {
            ((zzavo) zzavk.zzalw()).zza(new zzavf(this), this.zzeha);
        } catch (RemoteException unused) {
        }
    }

    /* access modifiers changed from: protected */
    public final Status zzb(Status status) {
        return status;
    }
}

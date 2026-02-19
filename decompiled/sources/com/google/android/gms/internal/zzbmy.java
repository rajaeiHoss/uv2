package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

final class zzbmy extends zzbnd {
    private /* synthetic */ int zzgto = DriveFile.MODE_WRITE_ONLY;

    zzbmy(zzbmu zzbmu, GoogleApiClient googleApiClient, int i) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmg(this.zzgto), (zzbrm) new zzbnb(this));
    }
}

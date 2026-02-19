package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

final class zzbmz extends zzbng {
    private /* synthetic */ String zzgtp;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbmz(zzbmu zzbmu, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzgtp = str;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbrg(DriveId.zzhe(this.zzgtp), false), (zzbrm) new zzbne(this));
    }
}

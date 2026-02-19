package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveId;

final class zzbnu extends zzbnp {
    private /* synthetic */ DriveId zzguf;
    private /* synthetic */ int zzgug = 1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbnu(zzbnq zzbnq, GoogleApiClient googleApiClient, DriveId driveId, int i) {
        super(googleApiClient);
        this.zzguf = driveId;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbti(this.zzguf, this.zzgug), (zzbro) null, (String) null, (zzbrm) new zzbto(this));
    }
}

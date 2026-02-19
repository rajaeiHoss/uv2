package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.DriveFile;

final class zzbob extends zzbnd {
    private /* synthetic */ zzboa zzguo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbob(zzboa zzboa, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzguo = zzboa;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbsz(this.zzguo.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzguo.zzgul.getRequestId()), (zzbrm) new zzbtb(this, (DriveFile.DownloadProgressListener) null));
    }
}

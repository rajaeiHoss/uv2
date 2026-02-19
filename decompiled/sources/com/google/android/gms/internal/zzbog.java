package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzar;
import com.google.android.gms.drive.DriveFile;

final class zzbog extends zzbnd {
    private /* synthetic */ int zzgto;
    private /* synthetic */ DriveFile.DownloadProgressListener zzgur;
    private /* synthetic */ zzbof zzgus;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbog(zzbof zzbof, GoogleApiClient googleApiClient, int i, DriveFile.DownloadProgressListener downloadProgressListener) {
        super(googleApiClient);
        this.zzgus = zzbof;
        this.zzgto = i;
        this.zzgur = downloadProgressListener;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq) throws RemoteException {
        zza(zzar.zzal(((zzbrk) zzbnq.zzalw()).zza(new zzbsz(this.zzgus.getDriveId(), this.zzgto, 0), (zzbrm) new zzbtb(this, this.zzgur)).zzgwo));
    }
}

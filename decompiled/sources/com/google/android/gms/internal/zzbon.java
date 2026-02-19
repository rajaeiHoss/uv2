package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;

final class zzbon extends zzbma {
    private final zzn<DriveFolder.DriveFileResult> zzgbf;

    public zzbon(zzn<DriveFolder.DriveFileResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbop(status, (DriveFile) null));
    }

    public final void zza(zzbsd zzbsd) throws RemoteException {
        this.zzgbf.setResult(new zzbop(Status.zzftq, new zzbof(zzbsd.zzgss)));
    }
}

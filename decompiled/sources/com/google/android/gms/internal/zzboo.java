package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveFolder;

final class zzboo extends zzbma {
    private final zzn<DriveFolder.DriveFolderResult> zzgbf;

    public zzboo(zzn<DriveFolder.DriveFolderResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbor(status, (DriveFolder) null));
    }

    public final void zza(zzbsd zzbsd) throws RemoteException {
        this.zzgbf.setResult(new zzbor(Status.zzftq, new zzbok(zzbsd.zzgss)));
    }
}

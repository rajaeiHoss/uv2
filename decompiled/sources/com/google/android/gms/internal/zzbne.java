package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;

final class zzbne extends zzbma {
    private final zzn<DriveApi.DriveIdResult> zzgbf;

    public zzbne(zzn<DriveApi.DriveIdResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbnf(status, (DriveId) null));
    }

    public final void zza(zzbsd zzbsd) throws RemoteException {
        this.zzgbf.setResult(new zzbnf(Status.zzftq, zzbsd.zzgss));
    }

    public final void zza(zzbso zzbso) throws RemoteException {
        this.zzgbf.setResult(new zzbnf(Status.zzftq, new zzbmp(zzbso.zzgtf).getDriveId()));
    }
}

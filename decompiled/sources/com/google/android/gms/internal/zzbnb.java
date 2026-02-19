package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;

final class zzbnb extends zzbma {
    private final zzn<DriveApi.DriveContentsResult> zzgbf;

    zzbnb(zzn<DriveApi.DriveContentsResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbnc(status, (DriveContents) null));
    }

    public final void zza(zzbrx zzbrx) throws RemoteException {
        this.zzgbf.setResult(new zzbnc(Status.zzftq, new zzboa(zzbrx.zzgul)));
    }
}

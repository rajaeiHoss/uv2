package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;

final class zzbtb extends zzbma {
    private final zzn<DriveApi.DriveContentsResult> zzgbf;
    private final DriveFile.DownloadProgressListener zzgxw;

    zzbtb(zzn<DriveApi.DriveContentsResult> zzn, DriveFile.DownloadProgressListener downloadProgressListener) {
        this.zzgbf = zzn;
        this.zzgxw = downloadProgressListener;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbnc(status, (DriveContents) null));
    }

    public final void zza(zzbrx zzbrx) throws RemoteException {
        this.zzgbf.setResult(new zzbnc(zzbrx.zzgxc ? new Status(-1) : Status.zzftq, new zzboa(zzbrx.zzgul)));
    }

    public final void zza(zzbsb zzbsb) throws RemoteException {
        DriveFile.DownloadProgressListener downloadProgressListener = this.zzgxw;
        if (downloadProgressListener != null) {
            downloadProgressListener.onProgress(zzbsb.zzgxf, zzbsb.zzgxg);
        }
    }
}

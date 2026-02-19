package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbtz extends zzbtx<DriveFile> {
    public zzbtz(TaskCompletionSource<DriveFile> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbsd zzbsd) throws RemoteException {
        zzaqt().setResult(zzbsd.getDriveId().asDriveFile());
    }
}

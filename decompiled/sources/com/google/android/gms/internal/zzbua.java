package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbua extends zzbtx<DriveFolder> {
    public zzbua(TaskCompletionSource<DriveFolder> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbsd zzbsd) throws RemoteException {
        zzaqt().setResult(zzbsd.getDriveId().asDriveFolder());
    }
}

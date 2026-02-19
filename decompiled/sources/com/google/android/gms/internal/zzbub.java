package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbub extends zzbtx<DriveContents> {
    public zzbub(TaskCompletionSource<DriveContents> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbrx zzbrx) throws RemoteException {
        zzaqt().setResult(new zzboa(zzbrx.zzaqp()));
    }
}

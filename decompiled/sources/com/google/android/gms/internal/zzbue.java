package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzbue extends zzbtx<DriveContents> {
    public zzbue(TaskCompletionSource<DriveContents> taskCompletionSource) {
        super(taskCompletionSource);
    }

    public final void zza(zzbrx zzbrx) throws RemoteException {
        zzaqt().setResult(new zzboa(zzbrx.zzaqp()));
    }
}

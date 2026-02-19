package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpf extends zzde<zzbnq, DriveFolder> {
    zzbpf(zzboz zzboz) {
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<DriveFolder> taskCompletionSource) throws RemoteException {
        if (zzbnq.zzaqj() == null) {
            taskCompletionSource.setException(new ApiException(new Status(10, "Drive#SCOPE_FILE must be requested")));
        } else {
            taskCompletionSource.setResult(new zzbok(zzbnq.zzaqj()));
        }
    }
}

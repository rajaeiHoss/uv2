package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpp extends zzde<zzbnq, DriveContents> {
    zzbpp(zzboz zzboz) {
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<DriveContents> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmg(DriveFile.MODE_WRITE_ONLY), (zzbrm) new zzbub(taskCompletionSource));
    }
}

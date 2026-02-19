package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpg extends zzde<zzbnq, Void> {
    private /* synthetic */ DriveResource zzgvi;

    zzbpg(zzboz zzboz, DriveResource driveResource) {
        this.zzgvi = driveResource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtr(this.zzgvi.getDriveId()), (zzbrm) new zzbuf(taskCompletionSource));
    }
}

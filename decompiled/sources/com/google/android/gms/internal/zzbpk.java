package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpk extends zzde<zzbnq, Void> {
    private /* synthetic */ DriveResource zzgvi;

    zzbpk(zzboz zzboz, DriveResource driveResource) {
        this.zzgvi = driveResource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        if (zzbnq.zzgtt) {
            ((zzbrk) zzbnq.zzalw()).zza(new zzbly(1, this.zzgvi.getDriveId()), (zzbro) null, (String) null, (zzbrm) new zzbuf(taskCompletionSource));
            return;
        }
        throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpz extends zzde<zzbnq, MetadataBuffer> {
    private /* synthetic */ DriveResource zzgvi;

    zzbpz(zzboz zzboz, DriveResource driveResource) {
        this.zzgvi = driveResource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<MetadataBuffer> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbrt(this.zzgvi.getDriveId()), (zzbrm) new zzbuc(taskCompletionSource));
    }
}

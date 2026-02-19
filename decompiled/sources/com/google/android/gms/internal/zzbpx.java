package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpx extends zzde<zzbnq, Metadata> {
    private /* synthetic */ DriveResource zzgvi;

    zzbpx(zzboz zzboz, DriveResource driveResource) {
        this.zzgvi = driveResource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Metadata> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbrg(this.zzgvi.getDriveId(), false), (zzbrm) new zzbud(taskCompletionSource));
    }
}

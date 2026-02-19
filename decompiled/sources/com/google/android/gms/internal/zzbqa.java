package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

final class zzbqa extends zzde<zzbnq, Void> {
    private /* synthetic */ DriveResource zzgvi;
    private /* synthetic */ List zzgvt;

    zzbqa(zzboz zzboz, DriveResource driveResource, List list) {
        this.zzgvi = driveResource;
        this.zzgvt = list;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtm(this.zzgvi.getDriveId(), this.zzgvt), (zzbrm) new zzbuf(taskCompletionSource));
    }
}

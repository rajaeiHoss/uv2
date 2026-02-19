package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpi extends zzcq<zzbnq, zzbqe> {
    private /* synthetic */ DriveResource zzgvi;
    private /* synthetic */ zzbqe zzgvj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbpi(zzboz zzboz, zzci zzci, DriveResource driveResource, zzbqe zzbqe) {
        super(zzci);
        this.zzgvi = driveResource;
        this.zzgvj = zzbqe;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbly(1, this.zzgvi.getDriveId()), (zzbro) this.zzgvj.zzgwc, (String) null, (zzbrm) new zzbuf(taskCompletionSource));
    }
}

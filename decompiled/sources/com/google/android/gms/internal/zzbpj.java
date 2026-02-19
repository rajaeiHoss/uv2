package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpj extends zzdo<zzbnq, zzbqe> {
    private /* synthetic */ DriveResource zzgvi;
    private /* synthetic */ zzbqe zzgvj;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbpj(zzboz zzboz, zzck zzck, DriveResource driveResource, zzbqe zzbqe) {
        super(zzck);
        this.zzgvi = driveResource;
        this.zzgvj = zzbqe;
    }

    /* access modifiers changed from: protected */
    public final void zzc(zzbnq zzbnq, TaskCompletionSource<Boolean> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbti(this.zzgvi.getDriveId(), 1), (zzbro) this.zzgvj.zzgwc, (String) null, (zzbrm) new zzbty(taskCompletionSource));
    }
}

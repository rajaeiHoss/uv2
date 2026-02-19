package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpt extends zzde<zzbnq, Void> {
    private /* synthetic */ DriveContents zzgvp;

    zzbpt(zzboz zzboz, DriveContents driveContents) {
        this.zzgvp = driveContents;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmd(this.zzgvp.zzapl().getRequestId(), false), (zzbrm) new zzbuf(taskCompletionSource));
    }
}

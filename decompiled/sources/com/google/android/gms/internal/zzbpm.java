package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpm extends zzde<zzbnq, DriveContents> {
    private /* synthetic */ DriveFile zzgvk;
    private /* synthetic */ int zzgvl;

    zzbpm(zzboz zzboz, DriveFile driveFile, int i) {
        this.zzgvk = driveFile;
        this.zzgvl = i;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<DriveContents> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbsz(this.zzgvk.getDriveId(), this.zzgvl, 0), (zzbrm) new zzbue(taskCompletionSource));
    }
}

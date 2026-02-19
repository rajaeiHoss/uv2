package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpr extends zzde<zzbnq, DriveContents> {
    private /* synthetic */ DriveContents zzgvp;

    zzbpr(zzboz zzboz, DriveContents driveContents) {
        this.zzgvp = driveContents;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<DriveContents> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbsz(this.zzgvp.getDriveId(), DriveFile.MODE_WRITE_ONLY, this.zzgvp.zzapl().getRequestId()), (zzbrm) new zzbue(taskCompletionSource));
    }
}

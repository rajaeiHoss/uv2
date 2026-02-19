package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzr;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbps extends zzde<zzbnq, Void> {
    private /* synthetic */ MetadataChangeSet zzgup;
    private /* synthetic */ DriveContents zzgvp;
    private /* synthetic */ zzr zzgvq;

    zzbps(zzboz zzboz, zzr zzr, DriveContents driveContents, MetadataChangeSet metadataChangeSet) {
        this.zzgvq = zzr;
        this.zzgvp = driveContents;
        this.zzgup = metadataChangeSet;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        try {
            this.zzgvq.zza(zzbnq);
        } catch (IllegalStateException e) {
            taskCompletionSource.setException(e);
        }
        this.zzgvp.zzapm();
        this.zzgup.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmb(this.zzgvp.getDriveId(), this.zzgup.zzapv(), this.zzgvp.zzapl().getRequestId(), this.zzgvp.zzapl().zzapd(), this.zzgvq), (zzbrm) new zzbuf(taskCompletionSource));
    }
}

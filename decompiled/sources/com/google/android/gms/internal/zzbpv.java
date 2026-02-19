package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.ExecutionOptions;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.zzo;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpv extends zzbqd<DriveFile> {
    private /* synthetic */ DriveFolder zzgvr;
    private /* synthetic */ ExecutionOptions zzgvs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbpv(zzboz zzboz, MetadataChangeSet metadataChangeSet, DriveContents driveContents, DriveFolder driveFolder, ExecutionOptions executionOptions) {
        super(metadataChangeSet, driveContents);
        this.zzgvr = driveFolder;
        this.zzgvs = executionOptions;
    }

    /* access modifiers changed from: protected */
    public final void zzb(zzbnq zzbnq, TaskCompletionSource<DriveFile> taskCompletionSource) throws RemoteException {
        ((zzbrk) zzbnq.zzalw()).zza(new zzbml(this.zzgvr.getDriveId(), this.zzgvy.zzapv(), this.zzgvz, this.zzgtb, this.zzgvw), (zzbrm) new zzbtz(taskCompletionSource));
    }

    /* access modifiers changed from: protected */
    public final zzo zzaqo() {
        return zzo.zza(this.zzgvs);
    }
}

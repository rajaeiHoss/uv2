package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpw extends zzde<zzbnq, DriveFolder> {
    private /* synthetic */ MetadataChangeSet zzguw;
    private /* synthetic */ DriveFolder zzgvr;

    zzbpw(zzboz zzboz, MetadataChangeSet metadataChangeSet, DriveFolder driveFolder) {
        this.zzguw = metadataChangeSet;
        this.zzgvr = driveFolder;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<DriveFolder> taskCompletionSource) throws RemoteException {
        this.zzguw.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbmn(this.zzgvr.getDriveId(), this.zzguw.zzapv()), (zzbrm) new zzbua(taskCompletionSource));
    }
}

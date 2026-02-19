package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzbpy extends zzde<zzbnq, Metadata> {
    private /* synthetic */ MetadataChangeSet zzguw;
    private /* synthetic */ DriveResource zzgvi;

    zzbpy(zzboz zzboz, MetadataChangeSet metadataChangeSet, DriveResource driveResource) {
        this.zzguw = metadataChangeSet;
        this.zzgvi = driveResource;
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<Metadata> taskCompletionSource) throws RemoteException {
        this.zzguw.zzapv().setContext(zzbnq.getContext());
        ((zzbrk) zzbnq.zzalw()).zza(new zzbtv(this.zzgvi.getDriveId(), this.zzguw.zzapv()), (zzbrm) new zzbud(taskCompletionSource));
    }
}

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.metadata.internal.zzk;
import com.google.android.gms.drive.zzo;
import com.google.android.gms.drive.zzq;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzbqd<T> extends zzde<zzbnq, T> {
    int zzgtb;
    private final MetadataChangeSet zzgvu;
    private final DriveContents zzgvv;
    zzo zzgvw = zzaqo();
    private zzk zzgvx;
    MetadataChangeSet zzgvy;
    int zzgvz;

    zzbqd(MetadataChangeSet metadataChangeSet, DriveContents driveContents) {
        this.zzgvu = metadataChangeSet;
        this.zzgvv = driveContents;
        zzbok.zzb(metadataChangeSet);
        zzk zzhh = zzk.zzhh(metadataChangeSet.getMimeType());
        this.zzgvx = zzhh;
        if (zzhh != null && zzhh.isFolder()) {
            throw new IllegalArgumentException("May not create folders using this method. Use DriveFolderManagerClient#createFolder() instead of mime type application/vnd.google-apps.folder");
        } else if (driveContents == null) {
        } else {
            if (!(driveContents instanceof zzboa)) {
                throw new IllegalArgumentException("Only DriveContents obtained from the Drive API are accepted.");
            } else if (driveContents.getDriveId() != null) {
                throw new IllegalArgumentException("Only DriveContents obtained through DriveApi.newDriveContents are accepted for file creation.");
            } else if (driveContents.zzapn()) {
                throw new IllegalArgumentException("DriveContents are already closed.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(zzbnq zzbnq, TaskCompletionSource<T> taskCompletionSource) throws RemoteException {
        this.zzgvw.zza(zzbnq);
        String zzapt = this.zzgvw.zzapt();
        MetadataChangeSet zza = zzapt == null ? this.zzgvu : zzbok.zza(this.zzgvu, zzapt);
        this.zzgvy = zza;
        zza.zzapv().setContext(zzbnq.getContext());
        this.zzgvz = zzbok.zza(this.zzgvv, this.zzgvx);
        zzk zzk = this.zzgvx;
        this.zzgtb = (zzk == null || !zzk.zzarc()) ? 0 : 1;
        zzb(zzbnq, taskCompletionSource);
    }

    /* access modifiers changed from: protected */
    public abstract void zzb(zzbnq zzbnq, TaskCompletionSource<T> taskCompletionSource) throws RemoteException;

    /* access modifiers changed from: package-private */
    public zzo zzaqo() {
        return (zzo) new zzq().build();
    }
}

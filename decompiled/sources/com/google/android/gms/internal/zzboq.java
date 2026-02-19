package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveFolder;

abstract class zzboq extends zzbno<DriveFolder.DriveFileResult> {
    zzboq(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final DriveFolder.DriveFileResult zzb(Status status) {
        return new zzbop(status, (DriveFile) null);
    }
}

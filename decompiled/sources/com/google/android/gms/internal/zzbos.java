package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFolder;

abstract class zzbos extends zzbno<DriveFolder.DriveFolderResult> {
    zzbos(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final DriveFolder.DriveFolderResult zzb(Status status) {
        return new zzbor(status, (DriveFolder) null);
    }
}

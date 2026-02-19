package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveContents;

abstract class zzbnd extends zzbno<DriveApi.DriveContentsResult> {
    zzbnd(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final DriveApi.DriveContentsResult zzb(Status status) {
        return new zzbnc(status, (DriveContents) null);
    }
}

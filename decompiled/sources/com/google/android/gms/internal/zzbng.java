package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.DriveId;

abstract class zzbng extends zzbno<DriveApi.DriveIdResult> {
    zzbng(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final DriveApi.DriveIdResult zzb(Status status) {
        return new zzbnf(status, (DriveId) null);
    }
}

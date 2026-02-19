package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

abstract class zzbni extends zzbno<DriveApi.MetadataBufferResult> {
    zzbni(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    public final DriveApi.MetadataBufferResult zzb(Status status) {
        return new zzbnh(status, (MetadataBuffer) null, false);
    }
}

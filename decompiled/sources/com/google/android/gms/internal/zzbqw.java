package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;

abstract class zzbqw extends zzbno<DriveResource.MetadataResult> {
    private zzbqw(zzbql zzbql, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* synthetic */ zzbqw(zzbql zzbql, GoogleApiClient googleApiClient, zzbqm zzbqm) {
        this(zzbql, googleApiClient);
    }

    public final DriveResource.MetadataResult zzb(Status status) {
        return new zzbqv(status, (Metadata) null);
    }
}

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;

final class zzbqv implements DriveResource.MetadataResult {
    private final Status mStatus;
    private final Metadata zzgwn;

    public zzbqv(Status status, Metadata metadata) {
        this.mStatus = status;
        this.zzgwn = metadata;
    }

    public final Metadata getMetadata() {
        return this.zzgwn;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}

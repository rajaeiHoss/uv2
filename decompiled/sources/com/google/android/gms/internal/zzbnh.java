package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

public final class zzbnh implements DriveApi.MetadataBufferResult {
    private final Status mStatus;
    private final MetadataBuffer zzgtq;
    private final boolean zzgtr;

    public zzbnh(Status status, MetadataBuffer metadataBuffer, boolean z) {
        this.mStatus = status;
        this.zzgtq = metadataBuffer;
        this.zzgtr = z;
    }

    public final MetadataBuffer getMetadataBuffer() {
        return this.zzgtq;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final void release() {
        MetadataBuffer metadataBuffer = this.zzgtq;
        if (metadataBuffer != null) {
            metadataBuffer.release();
        }
    }
}

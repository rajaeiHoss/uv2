package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.TransferPreferences;

final class zzbnm implements DriveApi.zza {
    private final Status mStatus;
    private final TransferPreferences zzgts;

    private zzbnm(Status status, TransferPreferences transferPreferences) {
        this.mStatus = status;
        this.zzgts = transferPreferences;
    }

    /* synthetic */ zzbnm(Status status, TransferPreferences transferPreferences, zzbmv zzbmv) {
        this(status, transferPreferences);
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final TransferPreferences zzapk() {
        return this.zzgts;
    }
}

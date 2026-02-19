package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

final class zzbox implements DrivePreferencesApi.FileUploadPreferencesResult {
    private final Status mStatus;
    private final FileUploadPreferences zzgve;

    private zzbox(zzbot zzbot, Status status, FileUploadPreferences fileUploadPreferences) {
        this.mStatus = status;
        this.zzgve = fileUploadPreferences;
    }

    /* synthetic */ zzbox(zzbot zzbot, Status status, FileUploadPreferences fileUploadPreferences, zzbou zzbou) {
        this(zzbot, status, fileUploadPreferences);
    }

    public final FileUploadPreferences getFileUploadPreferences() {
        return this.zzgve;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}

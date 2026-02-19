package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

@Deprecated
public final class zzbot implements DrivePreferencesApi {
    public final PendingResult<DrivePreferencesApi.FileUploadPreferencesResult> getFileUploadPreferences(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zzbou(this, googleApiClient));
    }

    public final PendingResult<Status> setFileUploadPreferences(GoogleApiClient googleApiClient, FileUploadPreferences fileUploadPreferences) {
        if (fileUploadPreferences instanceof zzbre) {
            return googleApiClient.zze(new zzbov(this, googleApiClient, (zzbre) fileUploadPreferences));
        }
        throw new IllegalArgumentException("Invalid preference value");
    }
}

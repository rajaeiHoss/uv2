package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

abstract class zzboy extends zzbno<DrivePreferencesApi.FileUploadPreferencesResult> {
    private /* synthetic */ zzbot zzgvd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzboy(zzbot zzbot, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzgvd = zzbot;
    }

    /* access modifiers changed from: protected */
    public final DrivePreferencesApi.FileUploadPreferencesResult zzb(Status status) {
        return new zzbox(this.zzgvd, status, (FileUploadPreferences) null, (zzbou) null);
    }
}

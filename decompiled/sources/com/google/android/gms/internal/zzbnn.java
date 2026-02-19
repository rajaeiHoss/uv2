package com.google.android.gms.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.TransferPreferences;

abstract class zzbnn extends zzbno<DriveApi.zza> {
    public zzbnn(GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    /* access modifiers changed from: protected */
    public final DriveApi.zza zzb(Status status) {
        return new zzbnm(status, (TransferPreferences) null, (zzbmv) null);
    }
}

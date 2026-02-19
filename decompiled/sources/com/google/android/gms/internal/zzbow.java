package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DrivePreferencesApi;
import com.google.android.gms.drive.FileUploadPreferences;

final class zzbow extends zzbma {
    private final zzn<DrivePreferencesApi.FileUploadPreferencesResult> zzgbf;
    private /* synthetic */ zzbot zzgvd;

    private zzbow(zzbot zzbot, zzn<DrivePreferencesApi.FileUploadPreferencesResult> zzn) {
        this.zzgvd = zzbot;
        this.zzgbf = zzn;
    }

    /* synthetic */ zzbow(zzbot zzbot, zzn zzn, zzbou zzbou) {
        this(zzbot, zzn);
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbox(this.zzgvd, status, (FileUploadPreferences) null, (zzbou) null));
    }

    public final void zza(zzbrz zzbrz) throws RemoteException {
        this.zzgbf.setResult(new zzbox(this.zzgvd, Status.zzftq, zzbrz.zzgxd, (zzbou) null));
    }
}

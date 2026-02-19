package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.drive.TransferPreferences;
import com.google.android.gms.drive.TransferPreferencesBuilder;

final class zzbnl extends zzbma {
    private final zzn<DriveApi.zza> zzgbf;

    private zzbnl(zzn<DriveApi.zza> zzn) {
        this.zzgbf = zzn;
    }

    /* synthetic */ zzbnl(zzn zzn, zzbmv zzbmv) {
        this(zzn);
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbnm(status, (TransferPreferences) null, (zzbmv) null));
    }

    public final void zza(zzbrz zzbrz) throws RemoteException {
        this.zzgbf.setResult(new zzbnm(Status.zzftq, new TransferPreferencesBuilder((FileUploadPreferences) zzbrz.zzgxd).build(), (zzbmv) null));
    }

    public final void zza(zzbsq zzbsq) throws RemoteException {
        this.zzgbf.setResult(new zzbnm(Status.zzftq, zzbsq.zzgxr, (zzbmv) null));
    }
}

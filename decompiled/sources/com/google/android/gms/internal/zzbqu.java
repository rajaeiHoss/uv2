package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.drive.Metadata;

final class zzbqu extends zzbma {
    private final zzn<DriveResource.MetadataResult> zzgbf;

    public zzbqu(zzn<DriveResource.MetadataResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbqv(status, (Metadata) null));
    }

    public final void zza(zzbso zzbso) throws RemoteException {
        this.zzgbf.setResult(new zzbqv(Status.zzftq, new zzbmp(zzbso.zzgtf)));
    }
}

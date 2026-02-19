package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.drive.MetadataBuffer;

final class zzbqt extends zzbma {
    private final zzn<DriveApi.MetadataBufferResult> zzgbf;

    public zzbqt(zzn<DriveApi.MetadataBufferResult> zzn) {
        this.zzgbf = zzn;
    }

    public final void onError(Status status) throws RemoteException {
        this.zzgbf.setResult(new zzbnh(status, (MetadataBuffer) null, false));
    }

    public final void zza(zzbsl zzbsl) throws RemoteException {
        this.zzgbf.setResult(new zzbnh(Status.zzftq, new MetadataBuffer(zzbsl.zzgxq), false));
    }
}

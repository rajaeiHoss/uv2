package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.location.places.internal.zzw;

public final class zzd extends zzw {
    private final zzf zzivg;
    private final zze zzivh;

    public zzd(zze zze) {
        this.zzivg = null;
        this.zzivh = zze;
    }

    public zzd(zzf zzf) {
        this.zzivg = zzf;
        this.zzivh = null;
    }

    public final void zza(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException {
        this.zzivg.setResult(placePhotoMetadataResult);
    }

    public final void zza(PlacePhotoResult placePhotoResult) throws RemoteException {
        this.zzivh.setResult(placePhotoResult);
    }
}

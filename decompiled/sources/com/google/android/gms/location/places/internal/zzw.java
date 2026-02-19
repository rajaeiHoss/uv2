package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.location.places.PlacePhotoMetadataResult;
import com.google.android.gms.location.places.PlacePhotoResult;

public abstract class zzw extends zzew implements zzv {
    public zzw() {
        attachInterface(this, "com.google.android.gms.location.places.internal.IPhotosCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        if (i == 2) {
            zza((PlacePhotoResult) zzex.zza(parcel, PlacePhotoResult.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            zza((PlacePhotoMetadataResult) zzex.zza(parcel, PlacePhotoMetadataResult.CREATOR));
        }
        return true;
    }
}

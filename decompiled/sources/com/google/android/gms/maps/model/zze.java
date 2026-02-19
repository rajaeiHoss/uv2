package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<LatLngBounds> {
    public final LatLngBounds createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        LatLng latLng = null;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                latLng = (LatLng) zzbgm.zza(parcel, readInt, LatLng.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                latLng2 = (LatLng) zzbgm.zza(parcel, readInt, LatLng.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LatLngBounds(latLng, latLng2);
    }

    public final LatLngBounds[] newArray(int i) {
        return new LatLngBounds[i];
    }
}

package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzj implements Parcelable.Creator<PointOfInterest> {
    public final PointOfInterest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                latLng = (LatLng) zzbgm.zza(parcel, readInt, LatLng.CREATOR);
            } else if (i == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str2 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PointOfInterest(latLng, str, str2);
    }

    public final PointOfInterest[] newArray(int i) {
        return new PointOfInterest[i];
    }
}

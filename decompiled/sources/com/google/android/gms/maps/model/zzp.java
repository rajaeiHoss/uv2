package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzp implements Parcelable.Creator<StreetViewPanoramaOrientation> {
    public final StreetViewPanoramaOrientation createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                f = zzbgm.zzl(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f2 = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new StreetViewPanoramaOrientation(f, f2);
    }

    public final StreetViewPanoramaOrientation[] newArray(int i) {
        return new StreetViewPanoramaOrientation[i];
    }
}

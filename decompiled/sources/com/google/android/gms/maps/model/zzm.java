package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzm implements Parcelable.Creator<StreetViewPanoramaCamera> {
    public final StreetViewPanoramaCamera createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                f = zzbgm.zzl(parcel, readInt);
            } else if (i == 3) {
                f2 = zzbgm.zzl(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                f3 = zzbgm.zzl(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new StreetViewPanoramaCamera(f, f2, f3);
    }

    public final StreetViewPanoramaCamera[] newArray(int i) {
        return new StreetViewPanoramaCamera[i];
    }
}

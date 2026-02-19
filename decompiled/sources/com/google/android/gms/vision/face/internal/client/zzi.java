package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<LandmarkParcel> {
    public final /* synthetic */ LandmarkParcel createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        float f = 0.0f;
        float f2 = 0.0f;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                f = zzbgm.zzl(parcel, readInt);
            } else if (i3 == 3) {
                f2 = zzbgm.zzl(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new LandmarkParcel(i, f, f2, i2);
    }

    public final /* synthetic */ LandmarkParcel[] newArray(int i) {
        return new LandmarkParcel[i];
    }
}

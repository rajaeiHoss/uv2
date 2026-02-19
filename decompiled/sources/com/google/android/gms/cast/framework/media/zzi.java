package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzi implements Parcelable.Creator<ImageHints> {
    public final /* synthetic */ ImageHints createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 3) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i4 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i3 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new ImageHints(i, i2, i3);
    }

    public final /* synthetic */ ImageHints[] newArray(int i) {
        return new ImageHints[i];
    }
}

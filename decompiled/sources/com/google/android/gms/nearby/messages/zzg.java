package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzg implements Parcelable.Creator<Strategy> {
    public final /* synthetic */ Strategy createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i8 = 65535 & readInt;
            if (i8 != 1000) {
                switch (i8) {
                    case 1:
                        i2 = zzbgm.zzg(parcel, readInt);
                        break;
                    case 2:
                        i3 = zzbgm.zzg(parcel, readInt);
                        break;
                    case 3:
                        i4 = zzbgm.zzg(parcel, readInt);
                        break;
                    case 4:
                        z = zzbgm.zzc(parcel, readInt);
                        break;
                    case 5:
                        i5 = zzbgm.zzg(parcel, readInt);
                        break;
                    case 6:
                        i6 = zzbgm.zzg(parcel, readInt);
                        break;
                    case 7:
                        i7 = zzbgm.zzg(parcel, readInt);
                        break;
                    default:
                        zzbgm.zzb(parcel, readInt);
                        break;
                }
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Strategy(i, i2, i3, i4, z, i5, i6, i7);
    }

    public final /* synthetic */ Strategy[] newArray(int i) {
        return new Strategy[i];
    }
}

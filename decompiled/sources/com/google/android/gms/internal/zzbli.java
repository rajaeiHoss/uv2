package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbli implements Parcelable.Creator<zzblh> {
    public final zzblh createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 4:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbgm.zzg(parcel, readInt);
                    break;
                case 7:
                    i3 = zzbgm.zzg(parcel, readInt);
                    break;
                case 8:
                    str4 = zzbgm.zzq(parcel, readInt);
                    break;
                case 9:
                    str5 = zzbgm.zzq(parcel, readInt);
                    break;
                case 10:
                    i4 = zzbgm.zzg(parcel, readInt);
                    break;
                case 11:
                    i5 = zzbgm.zzg(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzblh(str, str2, i, str3, i2, i3, str4, str5, i4, i5);
    }

    public final zzblh[] newArray(int i) {
        return new zzblh[i];
    }
}

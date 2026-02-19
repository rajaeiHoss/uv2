package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecj implements Parcelable.Creator<zzeci> {
    public final zzeci createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 4:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 5:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    str7 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 9:
                    str8 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 10:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 11:
                    z2 = zzbgm.zzc(parcel2, readInt);
                    break;
                case 12:
                    str9 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 13:
                    str10 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 14:
                    str11 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzeci(str, str2, str3, str4, str5, str6, str7, str8, z, z2, str9, str10, str11);
    }

    public final zzeci[] newArray(int i) {
        return new zzeci[i];
    }
}

package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzm implements Parcelable.Creator<zzl> {
    public final zzl createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i = 0;
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 3:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 4:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 9:
                    b = zzbgm.zze(parcel2, readInt);
                    break;
                case 10:
                    b2 = zzbgm.zze(parcel2, readInt);
                    break;
                case 11:
                    b3 = zzbgm.zze(parcel2, readInt);
                    break;
                case 12:
                    b4 = zzbgm.zze(parcel2, readInt);
                    break;
                case 13:
                    str7 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzl(i, str, str2, str3, str4, str5, str6, b, b2, b3, b4, str7);
    }

    public final zzl[] newArray(int i) {
        return new zzl[i];
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzcwn;
import java.util.HashSet;

public final class zzcwu implements Parcelable.Creator<zzcwn.zzd> {
    public final zzcwn.zzd createFromParcel(Parcel parcel) {
        int i;
        int zzd = zzbgm.zzd(parcel);
        HashSet hashSet = new HashSet();
        int i2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbgm.zzg(parcel, readInt);
                    i = 1;
                    break;
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    i = 2;
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel, readInt);
                    i = 3;
                    break;
                case 4:
                    str3 = zzbgm.zzq(parcel, readInt);
                    i = 4;
                    break;
                case 5:
                    str4 = zzbgm.zzq(parcel, readInt);
                    i = 5;
                    break;
                case 6:
                    str5 = zzbgm.zzq(parcel, readInt);
                    i = 6;
                    break;
                case 7:
                    str6 = zzbgm.zzq(parcel, readInt);
                    i = 7;
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    continue;
            }
            hashSet.add(Integer.valueOf(i));
        }
        if (parcel.dataPosition() == zzd) {
            return new zzcwn.zzd(hashSet, i2, str, str2, str3, str4, str5, str6);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzd);
        throw new zzbgn(sb.toString(), parcel);
    }

    public final zzcwn.zzd[] newArray(int i) {
        return new zzcwn.zzd[i];
    }
}

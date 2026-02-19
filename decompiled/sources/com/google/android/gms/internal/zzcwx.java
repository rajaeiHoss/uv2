package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzcwn;
import java.util.HashSet;

public final class zzcwx implements Parcelable.Creator<zzcwn.zzg> {
    public final zzcwn.zzg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        String str2 = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            int i5 = 1;
            if (i4 != 1) {
                i5 = 3;
                if (i4 != 3) {
                    i5 = 4;
                    if (i4 != 4) {
                        i5 = 5;
                        if (i4 != 5) {
                            i5 = 6;
                            if (i4 != 6) {
                                zzbgm.zzb(parcel, readInt);
                            } else {
                                i2 = zzbgm.zzg(parcel, readInt);
                            }
                        } else {
                            str = zzbgm.zzq(parcel, readInt);
                        }
                    } else {
                        str2 = zzbgm.zzq(parcel, readInt);
                    }
                } else {
                    i3 = zzbgm.zzg(parcel, readInt);
                }
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
            hashSet.add(Integer.valueOf(i5));
        }
        if (parcel.dataPosition() == zzd) {
            return new zzcwn.zzg(hashSet, i, str, i2, str2, i3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzd);
        throw new zzbgn(sb.toString(), parcel);
    }

    public final zzcwn.zzg[] newArray(int i) {
        return new zzcwn.zzg[i];
    }
}

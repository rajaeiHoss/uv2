package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzcwn;
import java.util.HashSet;

public final class zzcwt implements Parcelable.Creator<zzcwn.zzc> {
    public final zzcwn.zzc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            int i3 = 1;
            if (i2 != 1) {
                i3 = 2;
                if (i2 != 2) {
                    zzbgm.zzb(parcel, readInt);
                } else {
                    str = zzbgm.zzq(parcel, readInt);
                }
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
            hashSet.add(Integer.valueOf(i3));
        }
        if (parcel.dataPosition() == zzd) {
            return new zzcwn.zzc(hashSet, i, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzd);
        throw new zzbgn(sb.toString(), parcel);
    }

    public final zzcwn.zzc[] newArray(int i) {
        return new zzcwn.zzc[i];
    }
}

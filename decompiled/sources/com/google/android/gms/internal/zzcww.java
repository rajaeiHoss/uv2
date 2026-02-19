package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzcwn;
import java.util.HashSet;

public final class zzcww implements Parcelable.Creator<zzcwn.zzf> {
    public final zzcwn.zzf createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        String str = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            int i3 = 1;
            if (i2 != 1) {
                i3 = 2;
                if (i2 != 2) {
                    i3 = 3;
                    if (i2 != 3) {
                        zzbgm.zzb(parcel, readInt);
                    } else {
                        str = zzbgm.zzq(parcel, readInt);
                    }
                } else {
                    z = zzbgm.zzc(parcel, readInt);
                }
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
            hashSet.add(Integer.valueOf(i3));
        }
        if (parcel.dataPosition() == zzd) {
            return new zzcwn.zzf(hashSet, i, z, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzd);
        throw new zzbgn(sb.toString(), parcel);
    }

    public final zzcwn.zzf[] newArray(int i) {
        return new zzcwn.zzf[i];
    }
}

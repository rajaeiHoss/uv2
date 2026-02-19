package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzcwn;
import java.util.HashSet;

public final class zzcws implements Parcelable.Creator<zzcwn.zzb.C0000zzb> {
    public final zzcwn.zzb.C0000zzb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        HashSet hashSet = new HashSet();
        String str = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            int i5 = 1;
            if (i4 != 1) {
                i5 = 2;
                if (i4 != 2) {
                    i5 = 3;
                    if (i4 != 3) {
                        i5 = 4;
                        if (i4 != 4) {
                            zzbgm.zzb(parcel, readInt);
                        } else {
                            i3 = zzbgm.zzg(parcel, readInt);
                        }
                    } else {
                        str = zzbgm.zzq(parcel, readInt);
                    }
                } else {
                    i2 = zzbgm.zzg(parcel, readInt);
                }
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
            hashSet.add(Integer.valueOf(i5));
        }
        if (parcel.dataPosition() == zzd) {
            return new zzcwn.zzb.C0000zzb(hashSet, i, i2, str, i3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzd);
        throw new zzbgn(sb.toString(), parcel);
    }

    public final zzcwn.zzb.C0000zzb[] newArray(int i) {
        return new zzcwn.zzb.C0000zzb[i];
    }
}

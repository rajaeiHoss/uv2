package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<Configuration> {
    public final /* synthetic */ Configuration createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzi[] zziArr = null;
        String[] strArr = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 3) {
                zziArr = (zzi[]) zzbgm.zzb(parcel, readInt, zzi.CREATOR);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                strArr = zzbgm.zzaa(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Configuration(i, zziArr, strArr);
    }

    public final /* synthetic */ Configuration[] newArray(int i) {
        return new Configuration[i];
    }
}

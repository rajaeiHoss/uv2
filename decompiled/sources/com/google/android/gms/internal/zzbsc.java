package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzi;
import java.util.ArrayList;

public final class zzbsc implements Parcelable.Creator<zzbsb> {
    public final zzbsb createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        ArrayList<zzi> arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i2 == 3) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i2 == 4) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 5) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzi.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbsb(j, j2, i, arrayList);
    }

    public final zzbsb[] newArray(int i) {
        return new zzbsb[i];
    }
}

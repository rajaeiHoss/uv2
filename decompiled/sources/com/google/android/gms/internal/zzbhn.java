package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbhn implements Parcelable.Creator<zzbhl> {
    public final zzbhl createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ArrayList<zzbhm> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzbhm.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbhl(i, arrayList);
    }

    public final zzbhl[] newArray(int i) {
        return new zzbhl[i];
    }
}

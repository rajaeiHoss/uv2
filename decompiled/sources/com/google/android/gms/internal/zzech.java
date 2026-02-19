package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzech implements Parcelable.Creator<zzecg> {
    public final zzecg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ArrayList<String> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzac(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzecg(i, arrayList);
    }

    public final zzecg[] newArray(int i) {
        return new zzecg[i];
    }
}

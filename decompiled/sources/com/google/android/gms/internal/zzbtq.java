package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbtq implements Parcelable.Creator<zzbtp> {
    public final zzbtp createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<String> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzac(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbtp(arrayList);
    }

    public final zzbtp[] newArray(int i) {
        return new zzbtp[i];
    }
}

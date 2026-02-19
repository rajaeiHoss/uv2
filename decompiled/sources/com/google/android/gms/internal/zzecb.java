package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzecb implements Parcelable.Creator<zzeca> {
    public final zzeca createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzeby> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzeby.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzeca(arrayList);
    }

    public final zzeca[] newArray(int i) {
        return new zzeca[i];
    }
}

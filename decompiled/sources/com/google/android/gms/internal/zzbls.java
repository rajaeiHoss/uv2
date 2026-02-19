package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzbls implements Parcelable.Creator<zzblr> {
    public final zzblr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzbjp> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzbjp.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzblr(arrayList);
    }

    public final zzblr[] newArray(int i) {
        return new zzblr[i];
    }
}

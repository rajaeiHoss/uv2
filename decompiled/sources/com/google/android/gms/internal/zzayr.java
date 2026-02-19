package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzayr implements Parcelable.Creator<zzayo> {
    public final zzayo createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzayp> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzayp.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzayo(arrayList);
    }

    public final zzayo[] newArray(int i) {
        return new zzayo[i];
    }
}

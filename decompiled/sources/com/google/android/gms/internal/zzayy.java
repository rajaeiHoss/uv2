package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.location.places.internal.zzai;
import java.util.ArrayList;

public final class zzayy implements Parcelable.Creator<zzayw> {
    public final zzayw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzai> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzai.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzayw(arrayList);
    }

    public final zzayw[] newArray(int i) {
        return new zzayw[i];
    }
}

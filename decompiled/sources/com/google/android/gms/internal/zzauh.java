package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

public final class zzauh implements Parcelable.Creator<zzaug> {
    public final zzaug createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        ArrayList<zzauo> arrayList = null;
        String[] strArr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, zzauo.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                strArr = zzbgm.zzaa(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaug(status, arrayList, strArr);
    }

    public final zzaug[] newArray(int i) {
        return new zzaug[i];
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzeqa implements Parcelable.Creator<zzepy> {
    public final zzepy createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzepy(str);
    }

    public final zzepy[] newArray(int i) {
        return new zzepy[i];
    }
}

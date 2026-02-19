package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzgf implements Parcelable.Creator<zzge> {
    public final zzge createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        long j = 0;
        ArrayList<zzfs> arrayList = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList = zzbgm.zzc(parcel, readInt, zzfs.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzge(i, j, arrayList);
    }

    public final zzge[] newArray(int i) {
        return new zzge[i];
    }
}

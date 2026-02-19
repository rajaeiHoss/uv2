package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public final class zzazx implements Parcelable.Creator<zzazw> {
    public final zzazw createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ArrayList<zzayq> arrayList = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 2) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                arrayList = zzbgm.zzc(parcel, readInt, zzayq.CREATOR);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzazw(i, arrayList, i2);
    }

    public final zzazw[] newArray(int i) {
        return new zzazw[i];
    }
}

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzo implements Parcelable.Creator<Device> {
    public final /* synthetic */ Device createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 4) {
                str3 = zzbgm.zzq(parcel, readInt);
            } else if (i3 == 5) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i2 = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new Device(str, str2, str3, i, i2);
    }

    public final /* synthetic */ Device[] newArray(int i) {
        return new Device[i];
    }
}

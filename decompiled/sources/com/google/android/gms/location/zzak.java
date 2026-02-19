package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzak implements Parcelable.Creator<zzaj> {
    public final /* synthetic */ zzaj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = -1;
        long j2 = -1;
        int i = 1;
        int i2 = 1;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i3 = 65535 & readInt;
            if (i3 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i3 == 3) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i3 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j2 = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaj(i, i2, j, j2);
    }

    public final /* synthetic */ zzaj[] newArray(int i) {
        return new zzaj[i];
    }
}

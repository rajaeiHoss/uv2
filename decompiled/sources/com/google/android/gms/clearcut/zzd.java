package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzd implements Parcelable.Creator<zzc> {
    public final /* synthetic */ zzc createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i == 2) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzc(z, j, j2);
    }

    public final /* synthetic */ zzc[] newArray(int i) {
        return new zzc[i];
    }
}

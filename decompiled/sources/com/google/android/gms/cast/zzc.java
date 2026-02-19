package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzc implements Parcelable.Creator<AdBreakStatus> {
    public final /* synthetic */ AdBreakStatus createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                j = zzbgm.zzi(parcel, readInt);
            } else if (i == 3) {
                j2 = zzbgm.zzi(parcel, readInt);
            } else if (i == 4) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 5) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 6) {
                zzbgm.zzb(parcel, readInt);
            } else {
                j3 = zzbgm.zzi(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AdBreakStatus(j, j2, str, str2, j3);
    }

    public final /* synthetic */ AdBreakStatus[] newArray(int i) {
        return new AdBreakStatus[i];
    }
}

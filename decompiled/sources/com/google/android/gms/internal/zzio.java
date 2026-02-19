package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class zzio implements Parcelable.Creator<zzin> {
    public final zzin createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Bundle bundle = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 4:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    bundle = zzbgm.zzs(parcel2, readInt);
                    break;
                case 8:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 9:
                    j2 = zzbgm.zzi(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzin(str, j, str2, str3, str4, bundle, z, j2);
    }

    public final zzin[] newArray(int i) {
        return new zzin[i];
    }
}

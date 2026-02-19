package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcfe implements Parcelable.Creator<zzcfd> {
    public final zzcfd createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 4:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel, readInt);
                    break;
                case 6:
                    str4 = zzbgm.zzq(parcel, readInt);
                    break;
                case 7:
                    str6 = zzbgm.zzq(parcel, readInt);
                    break;
                case 8:
                    str7 = zzbgm.zzq(parcel, readInt);
                    break;
                case 9:
                    str5 = zzbgm.zzq(parcel, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzcfd(str, i, str2, str3, str4, str5, str6, str7);
    }

    public final zzcfd[] newArray(int i) {
        return new zzcfd[i];
    }
}

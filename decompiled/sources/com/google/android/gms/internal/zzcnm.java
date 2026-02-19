package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcnm implements Parcelable.Creator<zzcnl> {
    public final zzcnl createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        Long l = null;
        Float f = null;
        String str2 = null;
        String str3 = null;
        Double d = null;
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 2:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 4:
                    l = zzbgm.zzj(parcel2, readInt);
                    break;
                case 5:
                    f = zzbgm.zzm(parcel2, readInt);
                    break;
                case 6:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    d = zzbgm.zzo(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzcnl(i, str, j, l, f, str2, str3, d);
    }

    public final zzcnl[] newArray(int i) {
        return new zzcnl[i];
    }
}

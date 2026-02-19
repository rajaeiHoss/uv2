package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcig implements Parcelable.Creator<zzcif> {
    public final zzcif createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        long j5 = -2147483648L;
        boolean z = true;
        boolean z2 = false;
        int i = 0;
        boolean z3 = true;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 3:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 4:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 5:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 7:
                    j2 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 8:
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 9:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 10:
                    z2 = zzbgm.zzc(parcel2, readInt);
                    break;
                case 11:
                    j5 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 12:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 13:
                    j3 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 14:
                    j4 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 15:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 16:
                    z3 = zzbgm.zzc(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zzcif(str, str2, str3, str4, j, j2, str5, z, z2, j5, str6, j3, j4, i, z3);
    }

    public final zzcif[] newArray(int i) {
        return new zzcif[i];
    }
}

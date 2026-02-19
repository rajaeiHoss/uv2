package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        boolean z = false;
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
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    str7 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 9:
                    str8 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 10:
                    str9 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 11:
                    z = zzbgm.zzc(parcel2, readInt);
                    break;
                case 12:
                    str10 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new zza(str, str2, str3, str4, str5, str6, str7, str8, str9, z, str10);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}

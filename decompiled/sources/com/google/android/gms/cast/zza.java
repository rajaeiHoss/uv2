package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zza implements Parcelable.Creator<AdBreakClipInfo> {
    public final /* synthetic */ AdBreakClipInfo createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
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
                    j = zzbgm.zzi(parcel2, readInt);
                    break;
                case 5:
                    str3 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 6:
                    str4 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 7:
                    str5 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 8:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 9:
                    str7 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 10:
                    str8 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 11:
                    j2 = zzbgm.zzi(parcel2, readInt);
                    break;
                case 12:
                    str9 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new AdBreakClipInfo(str, str2, j, str3, str4, str5, str6, str7, str8, j2, str9);
    }

    public final /* synthetic */ AdBreakClipInfo[] newArray(int i) {
        return new AdBreakClipInfo[i];
    }
}

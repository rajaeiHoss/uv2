package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.vision.barcode.Barcode;

public final class zzg implements Parcelable.Creator<Barcode.DriverLicense> {
    public final /* synthetic */ Barcode.DriverLicense createFromParcel(Parcel parcel) {
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
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
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
                    str10 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 12:
                    str11 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 13:
                    str12 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 14:
                    str13 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 15:
                    str14 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new Barcode.DriverLicense(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    public final /* synthetic */ Barcode.DriverLicense[] newArray(int i) {
        return new Barcode.DriverLicense[i];
    }
}

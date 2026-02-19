package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzn implements Parcelable.Creator<CastDevice> {
    public final /* synthetic */ CastDevice createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        ArrayList<WebImage> arrayList = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        byte[] bArr = null;
        int i = 0;
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
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
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 8:
                    arrayList = zzbgm.zzc(parcel2, readInt, WebImage.CREATOR);
                    break;
                case 9:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 10:
                    i3 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 11:
                    str6 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 12:
                    str7 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 13:
                    i4 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 14:
                    str8 = zzbgm.zzq(parcel2, readInt);
                    break;
                case 15:
                    bArr = zzbgm.zzt(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new CastDevice(str, str2, str3, str4, str5, i, arrayList, i2, i3, str6, str7, i4, str8, bArr);
    }

    public final /* synthetic */ CastDevice[] newArray(int i) {
        return new CastDevice[i];
    }
}

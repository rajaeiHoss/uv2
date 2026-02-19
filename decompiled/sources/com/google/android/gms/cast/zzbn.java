package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzbn implements Parcelable.Creator<TextTrackStyle> {
    public final /* synthetic */ TextTrackStyle createFromParcel(Parcel parcel) {
        Parcel parcel2 = parcel;
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    f = zzbgm.zzl(parcel2, readInt);
                    break;
                case 3:
                    i = zzbgm.zzg(parcel2, readInt);
                    break;
                case 4:
                    i2 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 5:
                    i3 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 6:
                    i4 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 7:
                    i5 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 8:
                    i6 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 9:
                    i7 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 10:
                    str = zzbgm.zzq(parcel2, readInt);
                    break;
                case 11:
                    i8 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 12:
                    i9 = zzbgm.zzg(parcel2, readInt);
                    break;
                case 13:
                    str2 = zzbgm.zzq(parcel2, readInt);
                    break;
                default:
                    zzbgm.zzb(parcel2, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel2, zzd);
        return new TextTrackStyle(f, i, i2, i3, i4, i5, i6, i7, str, i8, i9, str2);
    }

    public final /* synthetic */ TextTrackStyle[] newArray(int i) {
        return new TextTrackStyle[i];
    }
}

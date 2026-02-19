package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzf implements Parcelable.Creator<zzap> {
    public final /* synthetic */ zzap createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<zzao> arrayList = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 2:
                    i2 = zzbgm.zzg(parcel, readInt);
                    break;
                case 3:
                    i3 = zzbgm.zzg(parcel, readInt);
                    break;
                case 4:
                    i4 = zzbgm.zzg(parcel, readInt);
                    break;
                case 5:
                    i5 = zzbgm.zzg(parcel, readInt);
                    break;
                case 6:
                    i6 = zzbgm.zzg(parcel, readInt);
                    break;
                case 7:
                    arrayList = zzbgm.zzc(parcel, readInt, zzao.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzap(i, i2, i3, i4, i5, i6, arrayList);
    }

    public final /* synthetic */ zzap[] newArray(int i) {
        return new zzap[i];
    }
}

package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzc implements Parcelable.Creator<zza> {
    public final /* synthetic */ zza createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<Integer> arrayList = null;
        String str2 = null;
        ArrayList<zzb> arrayList2 = null;
        String str3 = null;
        ArrayList<zzb> arrayList3 = null;
        String str4 = null;
        ArrayList<zzb> arrayList4 = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str2 = zzbgm.zzq(parcel, readInt);
                    break;
                case 2:
                    str = zzbgm.zzq(parcel, readInt);
                    break;
                case 3:
                    arrayList = zzbgm.zzab(parcel, readInt);
                    break;
                case 4:
                    arrayList2 = zzbgm.zzc(parcel, readInt, zzb.CREATOR);
                    break;
                case 5:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 6:
                    str3 = zzbgm.zzq(parcel, readInt);
                    break;
                case 7:
                    arrayList3 = zzbgm.zzc(parcel, readInt, zzb.CREATOR);
                    break;
                case 8:
                    str4 = zzbgm.zzq(parcel, readInt);
                    break;
                case 9:
                    arrayList4 = zzbgm.zzc(parcel, readInt, zzb.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zza(str, arrayList, i, str2, arrayList2, str3, arrayList3, str4, arrayList4);
    }

    public final /* synthetic */ zza[] newArray(int i) {
        return new zza[i];
    }
}

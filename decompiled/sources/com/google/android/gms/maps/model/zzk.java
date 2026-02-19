package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzk implements Parcelable.Creator<PolygonOptions> {
    public final PolygonOptions createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList<LatLng> arrayList2 = null;
        ArrayList<PatternItem> arrayList3 = null;
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    arrayList2 = zzbgm.zzc(parcel, readInt, LatLng.CREATOR);
                    break;
                case 3:
                    zzbgm.zza(parcel, readInt, (List) arrayList, getClass().getClassLoader());
                    break;
                case 4:
                    f = zzbgm.zzl(parcel, readInt);
                    break;
                case 5:
                    i = zzbgm.zzg(parcel, readInt);
                    break;
                case 6:
                    i2 = zzbgm.zzg(parcel, readInt);
                    break;
                case 7:
                    f2 = zzbgm.zzl(parcel, readInt);
                    break;
                case 8:
                    z = zzbgm.zzc(parcel, readInt);
                    break;
                case 9:
                    z2 = zzbgm.zzc(parcel, readInt);
                    break;
                case 10:
                    z3 = zzbgm.zzc(parcel, readInt);
                    break;
                case 11:
                    i3 = zzbgm.zzg(parcel, readInt);
                    break;
                case 12:
                    arrayList3 = zzbgm.zzc(parcel, readInt, PatternItem.CREATOR);
                    break;
                default:
                    zzbgm.zzb(parcel, readInt);
                    break;
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PolygonOptions(arrayList2, arrayList, f, i, i2, f2, z, z2, z3, i3, arrayList3);
    }

    public final PolygonOptions[] newArray(int i) {
        return new PolygonOptions[i];
    }
}

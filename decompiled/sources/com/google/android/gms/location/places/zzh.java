package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzh implements Parcelable.Creator<PlaceFilter> {
    public final /* synthetic */ PlaceFilter createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<Integer> arrayList = null;
        ArrayList<String> arrayList2 = null;
        ArrayList<zzo> arrayList3 = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzab(parcel, readInt);
            } else if (i == 6) {
                arrayList2 = zzbgm.zzac(parcel, readInt);
            } else if (i == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList3 = zzbgm.zzc(parcel, readInt, zzo.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new PlaceFilter((List<Integer>) arrayList, z, (List<String>) arrayList2, (List<zzo>) arrayList3);
    }

    public final /* synthetic */ PlaceFilter[] newArray(int i) {
        return new PlaceFilter[i];
    }
}

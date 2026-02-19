package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzc implements Parcelable.Creator<AutocompleteFilter> {
    public final /* synthetic */ AutocompleteFilter createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<Integer> arrayList = null;
        String str = null;
        int i = 0;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i2 == 2) {
                arrayList = zzbgm.zzab(parcel, readInt);
            } else if (i2 == 3) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i2 != 1000) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new AutocompleteFilter(i, z, arrayList, str);
    }

    public final /* synthetic */ AutocompleteFilter[] newArray(int i) {
        return new AutocompleteFilter[i];
    }
}

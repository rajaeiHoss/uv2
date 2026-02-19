package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zze implements Parcelable.Creator<CardRequirements> {
    public final /* synthetic */ CardRequirements createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        boolean z = false;
        ArrayList<Integer> arrayList = null;
        int i = 0;
        boolean z2 = true;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                arrayList = zzbgm.zzab(parcel, readInt);
            } else if (i2 == 2) {
                z2 = zzbgm.zzc(parcel, readInt);
            } else if (i2 == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new CardRequirements(arrayList, z2, z, i);
    }

    public final /* synthetic */ CardRequirements[] newArray(int i) {
        return new CardRequirements[i];
    }
}

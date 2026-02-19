package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzaa implements Parcelable.Creator<RawDataSet> {
    public final /* synthetic */ RawDataSet createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        ArrayList<RawDataPoint> arrayList = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i2 == 3) {
                arrayList = zzbgm.zzc(parcel, readInt, RawDataPoint.CREATOR);
            } else if (i2 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new RawDataSet(i, arrayList, z);
    }

    public final /* synthetic */ RawDataSet[] newArray(int i) {
        return new RawDataSet[i];
    }
}

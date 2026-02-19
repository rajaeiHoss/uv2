package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;

public final class zzd implements Parcelable.Creator<BleDevice> {
    public final /* synthetic */ BleDevice createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        String str2 = null;
        ArrayList<String> arrayList = null;
        ArrayList<DataType> arrayList2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i == 3) {
                arrayList = zzbgm.zzac(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                arrayList2 = zzbgm.zzc(parcel, readInt, DataType.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new BleDevice(str, str2, arrayList, arrayList2);
    }

    public final /* synthetic */ BleDevice[] newArray(int i) {
        return new BleDevice[i];
    }
}

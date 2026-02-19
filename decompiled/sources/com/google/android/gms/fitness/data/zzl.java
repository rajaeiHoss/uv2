package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzl implements Parcelable.Creator<DataType> {
    public final /* synthetic */ DataType createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        ArrayList<Field> arrayList = null;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i == 2) {
                arrayList = zzbgm.zzc(parcel, readInt, Field.CREATOR);
            } else if (i == 3) {
                str2 = zzbgm.zzq(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                str3 = zzbgm.zzq(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DataType(str, (List<Field>) arrayList, str2, str3);
    }

    public final /* synthetic */ DataType[] newArray(int i) {
        return new DataType[i];
    }
}

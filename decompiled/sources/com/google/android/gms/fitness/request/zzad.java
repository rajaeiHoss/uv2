package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzad implements Parcelable.Creator<GoalsReadRequest> {
    public final /* synthetic */ GoalsReadRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 2) {
                zzbgm.zza(parcel, readInt, (List) arrayList, getClass().getClassLoader());
            } else if (i == 3) {
                zzbgm.zza(parcel, readInt, (List) arrayList2, getClass().getClassLoader());
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbgm.zza(parcel, readInt, (List) arrayList3, getClass().getClassLoader());
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new GoalsReadRequest(iBinder, (List<DataType>) arrayList, (List<Integer>) arrayList2, (List<Integer>) arrayList3);
    }

    public final /* synthetic */ GoalsReadRequest[] newArray(int i) {
        return new GoalsReadRequest[i];
    }
}

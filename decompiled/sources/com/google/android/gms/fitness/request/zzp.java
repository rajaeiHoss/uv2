package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;
import java.util.ArrayList;
import java.util.List;

public final class zzp implements Parcelable.Creator<DataSourcesRequest> {
    public final /* synthetic */ DataSourcesRequest createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        ArrayList<DataType> arrayList = null;
        ArrayList<Integer> arrayList2 = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                arrayList = zzbgm.zzc(parcel, readInt, DataType.CREATOR);
            } else if (i == 2) {
                arrayList2 = zzbgm.zzab(parcel, readInt);
            } else if (i == 3) {
                z = zzbgm.zzc(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DataSourcesRequest((List<DataType>) arrayList, (List<Integer>) arrayList2, z, iBinder);
    }

    public final /* synthetic */ DataSourcesRequest[] newArray(int i) {
        return new DataSourcesRequest[i];
    }
}

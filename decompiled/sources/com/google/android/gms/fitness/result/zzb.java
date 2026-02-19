package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbgm;

public final class zzb implements Parcelable.Creator<DailyTotalResult> {
    public final /* synthetic */ DailyTotalResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        DataSet dataSet = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataSet = (DataSet) zzbgm.zza(parcel, readInt, DataSet.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DailyTotalResult(status, dataSet);
    }

    public final /* synthetic */ DailyTotalResult[] newArray(int i) {
        return new DailyTotalResult[i];
    }
}

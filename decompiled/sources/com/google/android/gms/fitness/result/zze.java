package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;

public final class zze implements Parcelable.Creator<DataTypeResult> {
    public final /* synthetic */ DataTypeResult createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        Status status = null;
        DataType dataType = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                status = (Status) zzbgm.zza(parcel, readInt, Status.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                dataType = (DataType) zzbgm.zza(parcel, readInt, DataType.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new DataTypeResult(status, dataType);
    }

    public final /* synthetic */ DataTypeResult[] newArray(int i) {
        return new DataTypeResult[i];
    }
}

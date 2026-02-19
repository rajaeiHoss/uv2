package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;

public final class zzbo implements Parcelable.Creator<zzbn> {
    public final /* synthetic */ zzbn createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataType dataType = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                dataType = (DataType) zzbgm.zza(parcel, readInt, DataType.CREATOR);
            } else if (i == 2) {
                dataSource = (DataSource) zzbgm.zza(parcel, readInt, DataSource.CREATOR);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbn(dataType, dataSource, iBinder);
    }

    public final /* synthetic */ zzbn[] newArray(int i) {
        return new zzbn[i];
    }
}

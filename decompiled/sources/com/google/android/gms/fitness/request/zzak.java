package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;

public final class zzak implements Parcelable.Creator<zzaj> {
    public final /* synthetic */ zzaj createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataType dataType = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                dataType = (DataType) zzbgm.zza(parcel, readInt, DataType.CREATOR);
            } else if (i != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaj(dataType, iBinder);
    }

    public final /* synthetic */ zzaj[] newArray(int i) {
        return new zzaj[i];
    }
}

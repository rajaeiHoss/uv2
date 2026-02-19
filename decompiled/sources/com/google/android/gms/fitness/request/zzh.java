package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgm;

public final class zzh implements Parcelable.Creator<zzg> {
    public final /* synthetic */ zzg createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        DataType dataType = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i == 2) {
                dataType = (DataType) zzbgm.zza(parcel, readInt, DataType.CREATOR);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzg(iBinder, dataType, z);
    }

    public final /* synthetic */ zzg[] newArray(int i) {
        return new zzg[i];
    }
}

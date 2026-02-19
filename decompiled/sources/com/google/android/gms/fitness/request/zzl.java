package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbgm;

public final class zzl implements Parcelable.Creator<zzk> {
    public final /* synthetic */ zzk createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        DataSet dataSet = null;
        IBinder iBinder = null;
        boolean z = false;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                dataSet = (DataSet) zzbgm.zza(parcel, readInt, DataSet.CREATOR);
            } else if (i == 2) {
                iBinder = zzbgm.zzr(parcel, readInt);
            } else if (i != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                z = zzbgm.zzc(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzk(dataSet, iBinder, z);
    }

    public final /* synthetic */ zzk[] newArray(int i) {
        return new zzk[i];
    }
}

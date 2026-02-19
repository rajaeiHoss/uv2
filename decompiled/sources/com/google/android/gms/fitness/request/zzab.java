package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzab implements Parcelable.Creator<zzaa> {
    public final /* synthetic */ zzaa createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzaa(iBinder);
    }

    public final /* synthetic */ zzaa[] newArray(int i) {
        return new zzaa[i];
    }
}

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzt implements Parcelable.Creator<zzs> {
    public final /* synthetic */ zzs createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        String str = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 1) {
                str = zzbgm.zzq(parcel, readInt);
            } else if (i != 3) {
                zzbgm.zzb(parcel, readInt);
            } else {
                iBinder = zzbgm.zzr(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzs(str, iBinder);
    }

    public final /* synthetic */ zzs[] newArray(int i) {
        return new zzs[i];
    }
}

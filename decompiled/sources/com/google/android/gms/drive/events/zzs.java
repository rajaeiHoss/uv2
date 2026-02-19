package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;
import com.google.android.gms.internal.zzblw;

public final class zzs implements Parcelable.Creator<zzr> {
    public final /* synthetic */ zzr createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzblw zzblw = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzblw = (zzblw) zzbgm.zza(parcel, readInt, zzblw.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzr(zzblw);
    }

    public final /* synthetic */ zzr[] newArray(int i) {
        return new zzr[i];
    }
}

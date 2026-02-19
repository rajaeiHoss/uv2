package com.google.android.gms.instantapps;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzj implements Parcelable.Creator<zzi> {
    public final /* synthetic */ zzi createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzi();
    }

    public final /* synthetic */ zzi[] newArray(int i) {
        return new zzi[i];
    }
}

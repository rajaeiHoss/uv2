package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzw implements Parcelable.Creator<zzv> {
    public final /* synthetic */ zzv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        FilterHolder filterHolder = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                filterHolder = (FilterHolder) zzbgm.zza(parcel, readInt, FilterHolder.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzv(filterHolder);
    }

    public final /* synthetic */ zzv[] newArray(int i) {
        return new zzv[i];
    }
}

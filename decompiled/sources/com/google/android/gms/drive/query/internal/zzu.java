package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgm;

public final class zzu implements Parcelable.Creator<zzt> {
    public final /* synthetic */ zzt createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        while (parcel.dataPosition() < zzd) {
            zzbgm.zzb(parcel, parcel.readInt());
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzt();
    }

    public final /* synthetic */ zzt[] newArray(int i) {
        return new zzt[i];
    }
}

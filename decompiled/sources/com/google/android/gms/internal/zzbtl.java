package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbtl implements Parcelable.Creator<zzbtk> {
    public final zzbtk createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        zzbre zzbre = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 2) {
                zzbgm.zzb(parcel, readInt);
            } else {
                zzbre = (zzbre) zzbgm.zza(parcel, readInt, zzbre.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbtk(zzbre);
    }

    public final zzbtk[] newArray(int i) {
        return new zzbtk[i];
    }
}

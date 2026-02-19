package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcty implements Parcelable.Creator<zzctx> {
    public final zzctx createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                zzbgm.zzb(parcel, readInt);
            } else {
                i = zzbgm.zzg(parcel, readInt);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzctx(i);
    }

    public final zzctx[] newArray(int i) {
        return new zzctx[i];
    }
}

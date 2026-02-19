package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbgm;

public final class zzbw implements Parcelable.Creator<zzbv> {
    public final /* synthetic */ zzbv createFromParcel(Parcel parcel) {
        int zzd = zzbgm.zzd(parcel);
        int i = 0;
        Scope[] scopeArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            int i4 = 65535 & readInt;
            if (i4 == 1) {
                i = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 2) {
                i2 = zzbgm.zzg(parcel, readInt);
            } else if (i4 == 3) {
                i3 = zzbgm.zzg(parcel, readInt);
            } else if (i4 != 4) {
                zzbgm.zzb(parcel, readInt);
            } else {
                scopeArr = (Scope[]) zzbgm.zzb(parcel, readInt, Scope.CREATOR);
            }
        }
        zzbgm.zzaf(parcel, zzd);
        return new zzbv(i, i2, i3, scopeArr);
    }

    public final /* synthetic */ zzbv[] newArray(int i) {
        return new zzbv[i];
    }
}

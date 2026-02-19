package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzbmg extends zzbgl {
    public static final Parcelable.Creator<zzbmg> CREATOR = new zzbmh();
    private int zzgpd;

    public zzbmg(int i) {
        zzbq.checkArgument(i == 536870912 || i == 805306368, "Cannot create a new read-only contents!");
        this.zzgpd = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzgpd);
        zzbgo.zzai(parcel, zze);
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzbst extends zzbgl {
    public static final Parcelable.Creator<zzbst> CREATOR = new zzbsu();
    private final List<String> zzgxs;

    zzbst(List<String> list) {
        this.zzgxs = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 2, this.zzgxs, false);
        zzbgo.zzai(parcel, zze);
    }
}

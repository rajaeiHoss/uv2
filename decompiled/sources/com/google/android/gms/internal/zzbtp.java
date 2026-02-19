package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public final class zzbtp extends zzbgl {
    public static final Parcelable.Creator<zzbtp> CREATOR = new zzbtq();
    private final List<String> zzgyb;

    public zzbtp(List<String> list) {
        this.zzgyb = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzb(parcel, 2, this.zzgyb, false);
        zzbgo.zzai(parcel, zze);
    }
}

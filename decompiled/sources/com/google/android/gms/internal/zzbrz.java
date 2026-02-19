package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbrz extends zzbgl {
    public static final Parcelable.Creator<zzbrz> CREATOR = new zzbsa();
    final zzbre zzgxd;

    public zzbrz(zzbre zzbre) {
        this.zzgxd = zzbre;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxd, i, false);
        zzbgo.zzai(parcel, zze);
    }
}

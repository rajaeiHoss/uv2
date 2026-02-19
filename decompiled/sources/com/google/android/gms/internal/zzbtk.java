package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbtk extends zzbgl {
    public static final Parcelable.Creator<zzbtk> CREATOR = new zzbtl();
    private zzbre zzgxd;

    public zzbtk(zzbre zzbre) {
        this.zzgxd = zzbre;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgxd, i, false);
        zzbgo.zzai(parcel, zze);
    }
}

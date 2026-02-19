package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsx extends zzbgl {
    public static final Parcelable.Creator<zzbsx> CREATOR = new zzbsy();
    private boolean zzgtr;

    public zzbsx(boolean z) {
        this.zzgtr = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgtr);
        zzbgo.zzai(parcel, zze);
    }
}

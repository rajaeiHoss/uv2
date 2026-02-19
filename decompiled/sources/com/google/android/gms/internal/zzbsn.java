package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbsn extends zzbgl {
    public static final Parcelable.Creator<zzbsn> CREATOR = new zzbss();
    private boolean zzarf;

    public zzbsn(boolean z) {
        this.zzarf = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzarf);
        zzbgo.zzai(parcel, zze);
    }
}

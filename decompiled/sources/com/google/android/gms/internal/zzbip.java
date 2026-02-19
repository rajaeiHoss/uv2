package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzbip extends zzbgl {
    public static final Parcelable.Creator<zzbip> CREATOR = new zzbiq();
    private final String mName;
    private final String mValue;

    public zzbip(String str, String str2) {
        this.mName = str;
        this.mValue = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.mName, false);
        zzbgo.zza(parcel, 3, this.mValue, false);
        zzbgo.zzai(parcel, zze);
    }
}

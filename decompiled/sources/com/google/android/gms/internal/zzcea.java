package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzcea extends zzbgl {
    public static final Parcelable.Creator<zzcea> CREATOR = new zzceb();
    private final String title;
    private final String zzipa;

    public zzcea(String str, String str2) {
        this.title = str;
        this.zzipa = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.title, false);
        zzbgo.zza(parcel, 3, this.zzipa, false);
        zzbgo.zzai(parcel, zze);
    }
}

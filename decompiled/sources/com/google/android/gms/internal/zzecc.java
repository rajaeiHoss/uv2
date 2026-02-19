package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzecc extends zzbgl {
    public static final Parcelable.Creator<zzecc> CREATOR = new zzecd();
    private String zzemh;
    private String zzmsw;
    private String zzmsx;

    public zzecc() {
    }

    zzecc(String str, String str2, String str3) {
        this.zzemh = str;
        this.zzmsw = str2;
        this.zzmsx = str3;
    }

    public final String getEmail() {
        return this.zzemh;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzemh, false);
        zzbgo.zza(parcel, 3, this.zzmsw, false);
        zzbgo.zza(parcel, 4, this.zzmsx, false);
        zzbgo.zzai(parcel, zze);
    }

    public final String zzbui() {
        return this.zzmsw;
    }

    public final String zzbuj() {
        return this.zzmsx;
    }
}

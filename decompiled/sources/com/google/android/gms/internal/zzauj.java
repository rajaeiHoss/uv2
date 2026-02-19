package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzauj extends zzbgl {
    public static final Parcelable.Creator<zzauj> CREATOR = new zzaul();
    private String name;
    private int weight;
    private String zzefx;
    private boolean zzefy;
    private boolean zzefz;
    private String zzega;
    private zzaue[] zzegb;
    private String zzegc;
    private zzaum zzegd;

    zzauj(String str, String str2, boolean z, int i, boolean z2, String str3, zzaue[] zzaueArr, String str4, zzaum zzaum) {
        this.name = str;
        this.zzefx = str2;
        this.zzefy = z;
        this.weight = i;
        this.zzefz = z2;
        this.zzega = str3;
        this.zzegb = zzaueArr;
        this.zzegc = str4;
        this.zzegd = zzaum;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.name, false);
        zzbgo.zza(parcel, 2, this.zzefx, false);
        zzbgo.zza(parcel, 3, this.zzefy);
        zzbgo.zzc(parcel, 4, this.weight);
        zzbgo.zza(parcel, 5, this.zzefz);
        zzbgo.zza(parcel, 6, this.zzega, false);
        zzbgo.zza(parcel, 7, this.zzegb, i, false);
        zzbgo.zza(parcel, 11, this.zzegc, false);
        zzbgo.zza(parcel, 12, (Parcelable) this.zzegd, i, false);
        zzbgo.zzai(parcel, zze);
    }
}

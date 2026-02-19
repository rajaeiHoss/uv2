package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlu extends zzbgl {
    public static final Parcelable.Creator<zzdlu> CREATOR = new zzdlv();
    public final String zzlib;
    public final zzdlf zzlih;
    private zzdlf zzlii;
    public final String zzlik;
    private float zzlil;
    private zzdlp[] zzlir;
    private boolean zzlis;

    public zzdlu(zzdlp[] zzdlpArr, zzdlf zzdlf, zzdlf zzdlf2, String str, float f, String str2, boolean z) {
        this.zzlir = zzdlpArr;
        this.zzlih = zzdlf;
        this.zzlii = zzdlf2;
        this.zzlik = str;
        this.zzlil = f;
        this.zzlib = str2;
        this.zzlis = z;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzlir, i, false);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzlih, i, false);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzlii, i, false);
        zzbgo.zza(parcel, 5, this.zzlik, false);
        zzbgo.zza(parcel, 6, this.zzlil);
        zzbgo.zza(parcel, 7, this.zzlib, false);
        zzbgo.zza(parcel, 8, this.zzlis);
        zzbgo.zzai(parcel, zze);
    }
}

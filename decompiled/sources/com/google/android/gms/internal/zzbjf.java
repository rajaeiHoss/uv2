package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import java.util.List;

public final class zzbjf extends zzbgl {
    public static final Parcelable.Creator<zzbjf> CREATOR = new zzbjg();
    private final String mPackageName;
    private final int zzglx;
    private final List<zzbip> zzgly;
    private final int zzglz;
    private final int zzgma;
    private final long zzgmk;
    private final DataHolder zzgml;
    private final String zzgmm;
    private final String zzgmn;
    private final String zzgmo;
    private final List<String> zzgmp;

    public zzbjf(String str, long j, DataHolder dataHolder, String str2, String str3, String str4, List<String> list, int i, List<zzbip> list2, int i2, int i3) {
        this.mPackageName = str;
        this.zzgmk = j;
        this.zzgml = dataHolder;
        this.zzgmm = str2;
        this.zzgmn = str3;
        this.zzgmo = str4;
        this.zzgmp = list;
        this.zzglx = i;
        this.zzgly = list2;
        this.zzgma = i2;
        this.zzglz = i3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.mPackageName, false);
        zzbgo.zza(parcel, 3, this.zzgmk);
        zzbgo.zza(parcel, 4, (Parcelable) this.zzgml, i, false);
        zzbgo.zza(parcel, 5, this.zzgmm, false);
        zzbgo.zza(parcel, 6, this.zzgmn, false);
        zzbgo.zza(parcel, 7, this.zzgmo, false);
        zzbgo.zzb(parcel, 8, this.zzgmp, false);
        zzbgo.zzc(parcel, 9, this.zzglx);
        zzbgo.zzc(parcel, 10, this.zzgly, false);
        zzbgo.zzc(parcel, 11, this.zzgma);
        zzbgo.zzc(parcel, 12, this.zzglz);
        zzbgo.zzai(parcel, zze);
    }
}

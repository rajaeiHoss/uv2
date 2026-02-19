package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzi;
import java.util.Collections;
import java.util.List;

public final class zzbsb extends zzbgl {
    public static final Parcelable.Creator<zzbsb> CREATOR = new zzbsc();
    private static final List<zzi> zzgxe = Collections.emptyList();
    private int zzcfl;
    final long zzgxf;
    final long zzgxg;
    private List<zzi> zzgxh;

    public zzbsb(long j, long j2, int i, List<zzi> list) {
        this.zzgxf = j;
        this.zzgxg = j2;
        this.zzcfl = i;
        this.zzgxh = list;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgxf);
        zzbgo.zza(parcel, 3, this.zzgxg);
        zzbgo.zzc(parcel, 4, this.zzcfl);
        zzbgo.zzc(parcel, 5, this.zzgxh, false);
        zzbgo.zzai(parcel, zze);
    }
}

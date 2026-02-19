package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.drive.zzc;

public final class zzbmd extends zzbgl {
    public static final Parcelable.Creator<zzbmd> CREATOR = new zzbme();
    private zzc zzgsu;
    private int zzgsw;
    private Boolean zzgsy;

    public zzbmd(int i, boolean z) {
        this((zzc) null, false, i);
    }

    zzbmd(zzc zzc, Boolean bool, int i) {
        this.zzgsu = zzc;
        this.zzgsy = bool;
        this.zzgsw = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzgsu, i, false);
        zzbgo.zza(parcel, 3, this.zzgsy, false);
        zzbgo.zzc(parcel, 4, this.zzgsw);
        zzbgo.zzai(parcel, zze);
    }
}

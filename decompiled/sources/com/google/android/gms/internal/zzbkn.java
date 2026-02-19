package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

@Deprecated
public final class zzbkn extends zzbgl {
    public static final Parcelable.Creator<zzbkn> CREATOR = new zzbko();
    public final String key;
    public final boolean zzgns;

    public zzbkn(boolean z, String str) {
        this.zzgns = z;
        this.key = zzbq.zzgv(str);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzgns);
        zzbgo.zza(parcel, 3, this.key, false);
        zzbgo.zzai(parcel, zze);
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzawr extends zzbgl {
    public static final Parcelable.Creator<zzawr> CREATOR = new zzaws();
    private String accountType;
    private int zzehz;

    zzawr(int i, String str) {
        this.zzehz = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
    }

    public zzawr(String str) {
        this(1, str);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, this.accountType, false);
        zzbgo.zzai(parcel, zze);
    }
}

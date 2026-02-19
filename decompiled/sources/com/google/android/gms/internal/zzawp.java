package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzawp extends zzbgl {
    public static final Parcelable.Creator<zzawp> CREATOR = new zzawq();
    private String accountType;
    private int zzehz;
    private int zzekj;

    zzawp(int i, String str, int i2) {
        this.zzehz = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
        this.zzekj = i2;
    }

    public zzawp(String str, int i) {
        this(1, str, i);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, this.accountType, false);
        zzbgo.zzc(parcel, 3, this.zzekj);
        zzbgo.zzai(parcel, zze);
    }
}

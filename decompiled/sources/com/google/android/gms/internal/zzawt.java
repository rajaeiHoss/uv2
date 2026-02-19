package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbq;

public final class zzawt extends zzbgl {
    public static final Parcelable.Creator<zzawt> CREATOR = new zzawu();
    private String accountType;
    private int zzehz;
    private byte[] zzekc;

    zzawt(int i, String str, byte[] bArr) {
        this.zzehz = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
        this.zzekc = (byte[]) zzbq.checkNotNull(bArr);
    }

    public zzawt(String str, byte[] bArr) {
        this(1, str, bArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzehz);
        zzbgo.zza(parcel, 2, this.accountType, false);
        zzbgo.zza(parcel, 3, this.zzekc, false);
        zzbgo.zzai(parcel, zze);
    }
}

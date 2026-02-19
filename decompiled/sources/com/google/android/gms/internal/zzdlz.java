package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdlz extends zzbgl {
    public static final Parcelable.Creator<zzdlz> CREATOR = new zzdma();
    private byte[] zzloj;

    zzdlz() {
        this(new byte[0]);
    }

    public zzdlz(byte[] bArr) {
        this.zzloj = bArr;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzloj, false);
        zzbgo.zzai(parcel, zze);
    }
}

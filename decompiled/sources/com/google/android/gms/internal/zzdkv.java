package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class zzdkv extends zzbgl {
    public static final Parcelable.Creator<zzdkv> CREATOR = new zzdkw();
    public int zzlgq;

    public zzdkv() {
    }

    public zzdkv(int i) {
        this.zzlgq = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.zzlgq);
        zzbgo.zzai(parcel, zze);
    }
}

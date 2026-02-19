package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzao extends zzbgl {
    public static final Parcelable.Creator<zzao> CREATOR = new zze();
    private int zziym;
    private int zziyn;

    zzao(int i, int i2) {
        this.zziym = i;
        this.zziyn = i2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zziym);
        zzbgo.zzc(parcel, 2, this.zziyn);
        zzbgo.zzai(parcel, zze);
    }
}

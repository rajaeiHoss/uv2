package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class zzdk extends zzbgl {
    public static final Parcelable.Creator<zzdk> CREATOR = new zzdl();
    public final int statusCode;
    public final zzah zzlui;

    public zzdk(int i, zzah zzah) {
        this.statusCode = i;
        this.zzlui = zzah;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 2, this.statusCode);
        zzbgo.zza(parcel, 3, (Parcelable) this.zzlui, i, false);
        zzbgo.zzai(parcel, zze);
    }
}

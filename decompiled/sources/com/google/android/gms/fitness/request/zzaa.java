package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;

public final class zzaa extends zzbgl {
    public static final Parcelable.Creator<zzaa> CREATOR = new zzab();
    private final zzbzt zzhnu;

    zzaa(IBinder iBinder) {
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzaa(zzbzt zzbzt) {
        this.zzhnu = zzbzt;
    }

    public final String toString() {
        return String.format("DisableFitRequest", new Object[0]);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhnu.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}

package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzcbu;
import com.google.android.gms.internal.zzcbv;

public final class zzah extends zzbgl {
    public static final Parcelable.Creator<zzah> CREATOR = new zzai();
    private final zzcbu zzhot;

    zzah(IBinder iBinder) {
        this.zzhot = zzcbv.zzbb(iBinder);
    }

    public zzah(zzcbu zzcbu) {
        this.zzhot = zzcbu;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhot.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}

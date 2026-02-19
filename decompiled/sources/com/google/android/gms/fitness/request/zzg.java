package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbyh;
import com.google.android.gms.internal.zzbyi;

public final class zzg extends zzbgl {
    public static final Parcelable.Creator<zzg> CREATOR = new zzh();
    private DataType zzhhj;
    private final zzbyh zzhnv;
    private final boolean zzhnw;

    zzg(IBinder iBinder, DataType dataType, boolean z) {
        this.zzhnv = zzbyi.zzas(iBinder);
        this.zzhhj = dataType;
        this.zzhnw = z;
    }

    public zzg(zzbyh zzbyh, DataType dataType, boolean z) {
        this.zzhnv = zzbyh;
        this.zzhhj = dataType;
        this.zzhnw = z;
    }

    public final String toString() {
        Object[] objArr = new Object[1];
        DataType dataType = this.zzhhj;
        objArr[0] = dataType == null ? "null" : dataType.zzary();
        return String.format("DailyTotalRequest{%s}", objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzhnv.asBinder(), false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhhj, i, false);
        zzbgo.zza(parcel, 4, this.zzhnw);
        zzbgo.zzai(parcel, zze);
    }
}

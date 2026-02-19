package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzk;
import com.google.android.gms.internal.zzbzl;

public final class zzaj extends zzbgl {
    public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
    private final DataType zzhhj;
    private final zzbzk zzhou;

    zzaj(DataType dataType, IBinder iBinder) {
        this.zzhhj = dataType;
        this.zzhou = zzbzl.zzax(iBinder);
    }

    public zzaj(DataType dataType, zzbzk zzbzk) {
        this.zzhhj = dataType;
        this.zzhou = zzbzk;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhj, i, false);
        zzbzk zzbzk = this.zzhou;
        zzbgo.zza(parcel, 2, zzbzk == null ? null : zzbzk.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataType;
import java.util.Collections;
import java.util.List;

public final class zzcby extends zzbgl {
    public static final Parcelable.Creator<zzcby> CREATOR = new zzcbz();
    private final List<DataType> zzhhz;

    public zzcby(List<DataType> list) {
        this.zzhhz = list;
    }

    public final List<DataType> getDataTypes() {
        return Collections.unmodifiableList(this.zzhhz);
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("dataTypes", this.zzhhz).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, Collections.unmodifiableList(this.zzhhz), false);
        zzbgo.zzai(parcel, zze);
    }
}

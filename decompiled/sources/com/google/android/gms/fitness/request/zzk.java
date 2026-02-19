package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;

public final class zzk extends zzbgl {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    private final DataSet zzhlq;
    private final zzbzt zzhnu;
    private final boolean zzhob;

    zzk(DataSet dataSet, IBinder iBinder, boolean z) {
        this.zzhlq = dataSet;
        this.zzhnu = zzbzu.zzba(iBinder);
        this.zzhob = z;
    }

    public zzk(DataSet dataSet, zzbzt zzbzt, boolean z) {
        this.zzhlq = dataSet;
        this.zzhnu = zzbzt;
        this.zzhob = z;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzk) && zzbg.equal(this.zzhlq, ((zzk) obj).zzhlq);
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhlq});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("dataSet", this.zzhlq).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhlq, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 2, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zza(parcel, 4, this.zzhob);
        zzbgo.zzai(parcel, zze);
    }
}

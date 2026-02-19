package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbyq;
import com.google.android.gms.internal.zzbyr;
import java.util.Arrays;

public final class zzs extends zzbgl {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();
    private final String name;
    private final zzbyq zzhoq;

    zzs(String str, IBinder iBinder) {
        this.name = str;
        this.zzhoq = zzbyr.zzav(iBinder);
    }

    public zzs(String str, zzbyq zzbyq) {
        this.name = str;
        this.zzhoq = zzbyq;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof zzs) && zzbg.equal(this.name, ((zzs) obj).name);
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("name", this.name).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.name, false);
        zzbgo.zza(parcel, 3, this.zzhoq.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}

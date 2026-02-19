package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbzt;
import com.google.android.gms.internal.zzbzu;
import java.util.Arrays;

public final class zzbn extends zzbgl {
    public static final Parcelable.Creator<zzbn> CREATOR = new zzbo();
    private final DataType zzhhj;
    private final DataSource zzhhk;
    private final zzbzt zzhnu;

    zzbn(DataType dataType, DataSource dataSource, IBinder iBinder) {
        this.zzhhj = dataType;
        this.zzhhk = dataSource;
        this.zzhnu = zzbzu.zzba(iBinder);
    }

    public zzbn(DataType dataType, DataSource dataSource, zzbzt zzbzt) {
        this.zzhhj = dataType;
        this.zzhhk = dataSource;
        this.zzhnu = zzbzt;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzbn) {
                zzbn zzbn = (zzbn) obj;
                if (zzbg.equal(this.zzhhk, zzbn.zzhhk) && zzbg.equal(this.zzhhj, zzbn.zzhhj)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhk, this.zzhhj});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, (Parcelable) this.zzhhj, i, false);
        zzbgo.zza(parcel, 2, (Parcelable) this.zzhhk, i, false);
        zzbzt zzbzt = this.zzhnu;
        zzbgo.zza(parcel, 3, zzbzt == null ? null : zzbzt.asBinder(), false);
        zzbgo.zzai(parcel, zze);
    }
}

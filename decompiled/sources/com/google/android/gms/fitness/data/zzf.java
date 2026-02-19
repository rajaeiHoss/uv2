package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.zzbwj;
import com.google.android.gms.internal.zzbwk;
import java.util.concurrent.TimeUnit;

final class zzf implements zzbwj<DataPoint, DataType> {
    public static final zzf zzhie = new zzf();

    private zzf() {
    }

    public final long zza(DataPoint dataPoint, TimeUnit timeUnit) {
        return dataPoint.getEndTime(timeUnit) - dataPoint.getStartTime(timeUnit);
    }

    public final DataType zzaa(DataPoint dataPoint) {
        return dataPoint.getDataType();
    }

    public final String zzab(DataPoint dataPoint) {
        return dataPoint.getDataType().getName();
    }

    public final zzbwk<DataType> zzarn() {
        return zzg.zzhif;
    }

    public final double zzb(DataPoint dataPoint, int i) {
        return (double) dataPoint.zzdb(i).asFloat();
    }

    public final int zzc(DataPoint dataPoint, int i) {
        return dataPoint.zzdb(i).asInt();
    }

    public final boolean zzd(DataPoint dataPoint, int i) {
        return dataPoint.zzdb(i).isSet();
    }
}

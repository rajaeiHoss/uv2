package com.google.android.gms.fitness.data;

import com.google.android.gms.internal.zzbwk;

final class zzg implements zzbwk<DataType> {
    public static final zzg zzhif = new zzg();

    private zzg() {
    }

    private static Field zza(DataType dataType, int i) {
        return dataType.getFields().get(i);
    }

    public final int zzac(DataType dataType) {
        return dataType.getFields().size();
    }

    public final String zzad(DataType dataType) {
        return dataType.getName();
    }

    public final int zze(DataType dataType, int i) {
        return zza(dataType, i).getFormat();
    }

    public final boolean zzf(DataType dataType, int i) {
        return Boolean.TRUE.equals(zza(dataType, i).isOptional());
    }

    public final String zzg(DataType dataType, int i) {
        return zza(dataType, i).getName();
    }

    public final boolean zzhm(String str) {
        return zzm.zzhn(str) != null;
    }
}

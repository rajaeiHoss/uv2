package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.query.Filter;
import java.util.List;

public final class zzk implements zzj<Boolean> {
    private Boolean zzhbx = false;

    private zzk() {
    }

    public static boolean zza(Filter filter) {
        if (filter == null) {
            return false;
        }
        return ((Boolean) filter.zza(new zzk())).booleanValue();
    }

    public final <T> Boolean zza(zzb<T> zzb, T t) {
        return this.zzhbx;
    }

    public final <T> Boolean zza(zzx zzx, MetadataField<T> metadataField, T t) {
        return this.zzhbx;
    }

    public final Boolean zza(zzx zzx, List<Boolean> list) {
        return this.zzhbx;
    }

    public final Boolean zzare() {
        return this.zzhbx;
    }

    public final Boolean zzarf() {
        return this.zzhbx;
    }

    public final <T> Boolean zzd(MetadataField<T> metadataField, T t) {
        return this.zzhbx;
    }

    public final Boolean zze(MetadataField<?> metadataField) {
        return this.zzhbx;
    }

    public final Boolean zzhj(String str) {
        if (!str.isEmpty()) {
            this.zzhbx = true;
        }
        return this.zzhbx;
    }

    public final Boolean zzy(Boolean bool) {
        return this.zzhbx;
    }
}

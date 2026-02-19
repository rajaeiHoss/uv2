package com.google.android.gms.drive.query;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.query.internal.zzj;
import com.google.android.gms.drive.query.internal.zzx;
import java.util.Iterator;
import java.util.List;

public final class zzd implements zzj<String> {
    public final <T> String zza(zzb<T> zzb, T t) {
        return String.format("contains(%s,%s)", new Object[]{zzb.getName(), t});
    }

    public final <T> String zza(zzx zzx, MetadataField<T> metadataField, T t) {
        return String.format("cmp(%s,%s,%s)", new Object[]{zzx.getTag(), metadataField.getName(), t});
    }

    public final String zza(zzx zzx, List<String> list) {
        StringBuilder sb = new StringBuilder(String.valueOf(zzx.getTag()).concat("("));
        Iterator<String> it = list.iterator();
        String str = "";
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next());
            str = ",";
        }
        sb.append(")");
        return sb.toString();
    }

    public final String zzare() {
        return "ownedByMe()";
    }

    public final String zzarf() {
        return "all()";
    }

    public final <T> String zzd(MetadataField<T> metadataField, T t) {
        return String.format("has(%s,%s)", new Object[]{metadataField.getName(), t});
    }

    public final String zze(MetadataField<?> metadataField) {
        return String.format("fieldOnly(%s)", new Object[]{metadataField.getName()});
    }

    public final String zzhj(String str) {
        return String.format("fullTextSearch(%s)", new Object[]{str});
    }

    public final String zzy(String str) {
        return String.format("not(%s)", new Object[]{str});
    }
}

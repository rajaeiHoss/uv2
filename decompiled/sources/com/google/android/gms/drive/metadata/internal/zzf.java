package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zzbuj;
import com.google.android.gms.internal.zzbuu;
import com.google.android.gms.internal.zzbuw;
import com.google.android.gms.internal.zzbve;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzf {
    private static final Map<String, MetadataField<?>> zzgyr = new HashMap();
    private static final Map<String, zzg> zzgys = new HashMap();

    static {
        zzb((MetadataField<?>) zzbuj.zzgyx);
        zzb((MetadataField<?>) zzbuj.zzhad);
        zzb((MetadataField<?>) zzbuj.zzgzu);
        zzb((MetadataField<?>) zzbuj.zzhab);
        zzb((MetadataField<?>) zzbuj.zzhae);
        zzb((MetadataField<?>) zzbuj.zzgzk);
        zzb((MetadataField<?>) zzbuj.zzgzj);
        zzb((MetadataField<?>) zzbuj.zzgzl);
        zzb((MetadataField<?>) zzbuj.zzgzm);
        zzb((MetadataField<?>) zzbuj.zzgzn);
        zzb((MetadataField<?>) zzbuj.zzgzh);
        zzb((MetadataField<?>) zzbuj.zzgzp);
        zzb((MetadataField<?>) zzbuj.zzgzq);
        zzb((MetadataField<?>) zzbuj.zzgzr);
        zzb((MetadataField<?>) zzbuj.zzgzz);
        zzb((MetadataField<?>) zzbuj.zzgyy);
        zzb((MetadataField<?>) zzbuj.zzgzw);
        zzb((MetadataField<?>) zzbuj.zzgza);
        zzb((MetadataField<?>) zzbuj.zzgzi);
        zzb((MetadataField<?>) zzbuj.zzgzb);
        zzb((MetadataField<?>) zzbuj.zzgzc);
        zzb((MetadataField<?>) zzbuj.zzgzd);
        zzb((MetadataField<?>) zzbuj.zzgze);
        zzb((MetadataField<?>) zzbuj.zzgzt);
        zzb((MetadataField<?>) zzbuj.zzgzo);
        zzb((MetadataField<?>) zzbuj.zzgzv);
        zzb((MetadataField<?>) zzbuj.zzgzx);
        zzb((MetadataField<?>) zzbuj.zzgzy);
        zzb((MetadataField<?>) zzbuj.zzhaa);
        zzb((MetadataField<?>) zzbuj.zzhaf);
        zzb((MetadataField<?>) zzbuj.zzhag);
        zzb((MetadataField<?>) zzbuj.zzgzg);
        zzb((MetadataField<?>) zzbuj.zzgzf);
        zzb((MetadataField<?>) zzbuj.zzhac);
        zzb((MetadataField<?>) zzbuj.zzgzs);
        zzb((MetadataField<?>) zzbuj.zzgyz);
        zzb((MetadataField<?>) zzbuj.zzhah);
        zzb((MetadataField<?>) zzbuj.zzhai);
        zzb((MetadataField<?>) zzbuj.zzhaj);
        zzb((MetadataField<?>) zzbuj.zzhak);
        zzb((MetadataField<?>) zzbuj.zzhal);
        zzb((MetadataField<?>) zzbuj.zzham);
        zzb((MetadataField<?>) zzbuj.zzhan);
        zzb((MetadataField<?>) zzbuw.zzhap);
        zzb((MetadataField<?>) zzbuw.zzhar);
        zzb((MetadataField<?>) zzbuw.zzhas);
        zzb((MetadataField<?>) zzbuw.zzhat);
        zzb((MetadataField<?>) zzbuw.zzhaq);
        zzb((MetadataField<?>) zzbuw.zzhau);
        zzb((MetadataField<?>) zzbve.zzhaw);
        zzb((MetadataField<?>) zzbve.zzhax);
        zza(zzo.zzgyw);
        zza(zzbuu.zzhao);
    }

    private static void zza(zzg zzg) {
        if (zzgys.put(zzg.zzaqy(), zzg) != null) {
            String zzaqy = zzg.zzaqy();
            StringBuilder sb = new StringBuilder(String.valueOf(zzaqy).length() + 46);
            sb.append("A cleaner for key ");
            sb.append(zzaqy);
            sb.append(" has already been registered");
            throw new IllegalStateException(sb.toString());
        }
    }

    public static Collection<MetadataField<?>> zzaqx() {
        return Collections.unmodifiableCollection(zzgyr.values());
    }

    public static void zzb(DataHolder dataHolder) {
        for (zzg zzc : zzgys.values()) {
            zzc.zzc(dataHolder);
        }
    }

    private static void zzb(MetadataField<?> metadataField) {
        Map<String, MetadataField<?>> map = zzgyr;
        if (map.containsKey(metadataField.getName())) {
            String valueOf = String.valueOf(metadataField.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Duplicate field name registered: ".concat(valueOf) : new String("Duplicate field name registered: "));
        } else {
            map.put(metadataField.getName(), metadataField);
        }
    }

    public static MetadataField<?> zzhg(String str) {
        return zzgyr.get(str);
    }
}

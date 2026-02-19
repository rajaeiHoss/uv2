package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import java.util.List;
import java.util.Map;

final class zzaz extends zzgi {
    private static final String ID = zzbh.DATA_LAYER_WRITE.toString();
    private static final String VALUE = zzbi.VALUE.toString();
    private static final String zzkpd = zzbi.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    private final DataLayer zzknd;

    public zzaz(DataLayer dataLayer) {
        super(ID, VALUE);
        this.zzknd = dataLayer;
    }

    public final void zzz(Map<String, zzbt> map) {
        String zzd;
        zzbt zzbt = map.get(VALUE);
        if (!(zzbt == null || zzbt == zzgk.zzbif())) {
            Object zzi = zzgk.zzi(zzbt);
            if (zzi instanceof List) {
                for (Object next : (List) zzi) {
                    if (next instanceof Map) {
                        this.zzknd.push((Map) next);
                    }
                }
            }
        }
        zzbt zzbt2 = map.get(zzkpd);
        if (zzbt2 != null && zzbt2 != zzgk.zzbif() && (zzd = zzgk.zzd(zzbt2)) != zzgk.zzbik()) {
            this.zzknd.zzlm(zzd);
        }
    }
}

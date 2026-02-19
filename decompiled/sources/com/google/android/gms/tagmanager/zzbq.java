package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbm;
import com.google.android.gms.internal.zzbn;
import com.google.android.gms.internal.zzbr;
import com.google.android.gms.internal.zzbt;
import java.util.Map;

final class zzbq {
    private static void zza(DataLayer dataLayer, zzbn zzbn) {
        for (zzbm zzbm : zzbn.zzws) {
            String str = null;
            if (zzbm.key == null) {
                str = "GaExperimentRandom: No key";
            } else {
                Object obj = dataLayer.get(zzbm.key);
                Long valueOf = !(obj instanceof Number) ? null : Long.valueOf(((Number) obj).longValue());
                long j = zzbm.zzwm;
                long j2 = zzbm.zzwn;
                if (!zzbm.zzwo || valueOf == null || valueOf.longValue() < j || valueOf.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        str = "GaExperimentRandom: random range invalid";
                    }
                }
                dataLayer.zzlm(zzbm.key);
                Map<String, Object> zzn = DataLayer.zzn(zzbm.key, obj);
                if (zzbm.zzwp > 0) {
                    if (!zzn.containsKey("gtm")) {
                        zzn.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(zzbm.zzwp)));
                    } else {
                        Object obj2 = zzn.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(zzbm.zzwp));
                        } else {
                            zzdj.zzcz("GaExperimentRandom: gtm not a map");
                        }
                    }
                }
                dataLayer.push(zzn);
            }
            if (str != null) {
                zzdj.zzcz(str);
            }
        }
    }

    public static void zza(DataLayer dataLayer, zzbr zzbr) {
        Map map;
        if (zzbr.zzyg == null) {
            zzdj.zzcz("supplemental missing experimentSupplemental");
            return;
        }
        for (zzbt zzd : zzbr.zzyg.zzwr) {
            dataLayer.zzlm(zzgk.zzd(zzd));
        }
        for (zzbt zzi : zzbr.zzyg.zzwq) {
            Object zzi2 = zzgk.zzi(zzi);
            if (!(zzi2 instanceof Map)) {
                String valueOf = String.valueOf(zzi2);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 36);
                sb.append("value: ");
                sb.append(valueOf);
                sb.append(" is not a map value, ignored.");
                zzdj.zzcz(sb.toString());
                map = null;
            } else {
                map = (Map) zzi2;
            }
            if (map != null) {
                dataLayer.push(map);
            }
        }
        zza(dataLayer, zzbr.zzyg);
    }
}

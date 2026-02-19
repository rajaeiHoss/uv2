package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class zzeih {
    public static zzegi zza(zzegi zzegi, Map<String, Object> map) {
        zzegi zzbxz = zzegi.zzbxz();
        Iterator<Map.Entry<zzegu, zzenn>> it = zzegi.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzbxz = zzbxz.zze((zzegu) next.getKey(), zza((zzenn) next.getValue(), map));
        }
        return zzbxz;
    }

    public static zzenn zza(zzenn zzenn, Map<String, Object> map) {
        Object value = zzenn.zzcce().getValue();
        if (value instanceof Map) {
            Map map2 = (Map) value;
            if (map2.containsKey(".sv")) {
                value = map.get((String) map2.get(".sv"));
            }
        }
        zzenn zzc = zzent.zzc((zzegu) null, value);
        if (zzenn.zzccd()) {
            Object value2 = zzenn.getValue();
            if (value2 instanceof Map) {
                Map map3 = (Map) value2;
                if (map3.containsKey(".sv")) {
                    String str = (String) map3.get(".sv");
                    if (map.containsKey(str)) {
                        value2 = map.get(str);
                    }
                }
            }
            return (!value2.equals(zzenn.getValue()) || !zzc.equals(zzenn.zzcce())) ? zzenq.zza(value2, zzc) : zzenn;
        } else if (zzenn.isEmpty()) {
            return zzenn;
        } else {
            zzems zzems = (zzems) zzenn;
            zzeik zzeik = new zzeik(zzems);
            zzems.zza(new zzeij(map, zzeik), false);
            return !zzeik.zzbza().zzcce().equals(zzc) ? zzeik.zzbza().zzf(zzc) : zzeik.zzbza();
        }
    }

    public static Map<String, Object> zza(zzeos zzeos) {
        HashMap hashMap = new HashMap();
        hashMap.put(AppMeasurement.Param.TIMESTAMP, Long.valueOf(zzeos.millis()));
        return hashMap;
    }
}

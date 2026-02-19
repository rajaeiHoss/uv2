package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzenq {
    public static zzenn zza(Object obj, zzenn zzenn) throws DatabaseException {
        HashMap hashMap;
        try {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (map.containsKey(".priority")) {
                    zzenn = zzent.zzc((zzegu) null, map.get(".priority"));
                }
                if (map.containsKey(".value")) {
                    obj = map.get(".value");
                }
            }
            if (obj == null) {
                return zzene.zzcco();
            }
            if (obj instanceof String) {
                return new zzenv((String) obj, zzenn);
            }
            if (obj instanceof Long) {
                return new zzenl((Long) obj, zzenn);
            }
            if (obj instanceof Integer) {
                return new zzenl(Long.valueOf((long) ((Integer) obj).intValue()), zzenn);
            }
            if (obj instanceof Double) {
                return new zzend((Double) obj, zzenn);
            }
            if (obj instanceof Boolean) {
                return new zzemp((Boolean) obj, zzenn);
            }
            if (!(obj instanceof Map)) {
                if (!(obj instanceof List)) {
                    String valueOf = String.valueOf(obj.getClass().toString());
                    throw new DatabaseException(valueOf.length() != 0 ? "Failed to parse node with class ".concat(valueOf) : new String("Failed to parse node with class "));
                }
            }
            if (obj instanceof Map) {
                Map map2 = (Map) obj;
                if (map2.containsKey(".sv")) {
                    return new zzenc(map2, zzenn);
                }
                hashMap = new HashMap(map2.size());
                for (Object keyObj : map2.keySet()) {
                    String str = (String) keyObj;
                    if (!str.startsWith(".")) {
                        zzenn zza = zza(map2.get(str), zzene.zzcco());
                        if (!zza.isEmpty()) {
                            hashMap.put(zzemq.zzqf(str), zza);
                        }
                    }
                }
            } else {
                List list = (List) obj;
                hashMap = new HashMap(list.size());
                for (int i = 0; i < list.size(); i++) {
                    StringBuilder sb = new StringBuilder(11);
                    sb.append(i);
                    String sb2 = sb.toString();
                    zzenn zza2 = zza(list.get(i), zzene.zzcco());
                    if (!zza2.isEmpty()) {
                        hashMap.put(zzemq.zzqf(sb2), zza2);
                    }
                }
            }
            return hashMap.isEmpty() ? zzene.zzcco() : new zzems(zzedr.zza(hashMap, zzems.zznoa), zzenn);
        } catch (ClassCastException e) {
            throw new DatabaseException("Failed to parse node", e);
        }
    }
}

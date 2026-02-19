package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzdcm {
    private static final Map<String, zzdcn> zzkzu;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(zzbh.CONTAINS.toString(), new zzdcn("contains"));
        hashMap.put(zzbh.ENDS_WITH.toString(), new zzdcn("endsWith"));
        hashMap.put(zzbh.EQUALS.toString(), new zzdcn("equals"));
        hashMap.put(zzbh.GREATER_EQUALS.toString(), new zzdcn("greaterEquals"));
        hashMap.put(zzbh.GREATER_THAN.toString(), new zzdcn("greaterThan"));
        hashMap.put(zzbh.LESS_EQUALS.toString(), new zzdcn("lessEquals"));
        hashMap.put(zzbh.LESS_THAN.toString(), new zzdcn("lessThan"));
        hashMap.put(zzbh.REGEX.toString(), new zzdcn("regex", new String[]{zzbi.ARG0.toString(), zzbi.ARG1.toString(), zzbi.IGNORE_CASE.toString()}));
        hashMap.put(zzbh.STARTS_WITH.toString(), new zzdcn("startsWith"));
        zzkzu = hashMap;
    }

    public static zzdkb zza(String str, Map<String, zzdjq<?>> map, zzdbb zzdbb) {
        Map<String, zzdcn> map2 = zzkzu;
        if (map2.containsKey(str)) {
            zzdcn zzdcn = map2.get(str);
            List<zzdjq<?>> zza = zza(zzdcn.zzbjl(), map);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new zzdkc("gtmUtils"));
            zzdkb zzdkb = new zzdkb("15", arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(zzdkb);
            arrayList2.add(new zzdkc("mobile"));
            zzdkb zzdkb2 = new zzdkb("17", arrayList2);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(zzdkb2);
            arrayList3.add(new zzdkc(zzdcn.zzbjk()));
            arrayList3.add(new zzdjx(zza));
            return new zzdkb("2", arrayList3);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 47);
        sb.append("Fail to convert ");
        sb.append(str);
        sb.append(" to the internal representation");
        throw new RuntimeException(sb.toString());
    }

    public static String zza(zzbh zzbh) {
        return zzmw(zzbh.toString());
    }

    private static List<zzdjq<?>> zza(String[] strArr, Map<String, zzdjq<?>> map) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            arrayList.add(!map.containsKey(strArr[i]) ? zzdjw.zzlcz : map.get(strArr[i]));
        }
        return arrayList;
    }

    public static String zzmw(String str) {
        Map<String, zzdcn> map = zzkzu;
        if (map.containsKey(str)) {
            return map.get(str).zzbjk();
        }
        return null;
    }
}

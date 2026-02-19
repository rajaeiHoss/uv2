package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public final class zzbiy implements zzbio {
    private final Status mStatus;
    private final Map<String, TreeMap<String, byte[]>> zzgmg;
    private final long zzgmh;
    private final List<byte[]> zzgmi;

    public zzbiy(Status status, Map<String, TreeMap<String, byte[]>> map) {
        this(status, map, -1);
    }

    private zzbiy(Status status, Map<String, TreeMap<String, byte[]>> map, long j) {
        this(status, map, -1, (List<byte[]>) null);
    }

    public zzbiy(Status status, Map<String, TreeMap<String, byte[]>> map, long j, List<byte[]> list) {
        this.mStatus = status;
        this.zzgmg = map;
        this.zzgmh = j;
        this.zzgmi = list;
    }

    public zzbiy(Status status, Map<String, TreeMap<String, byte[]>> map, List<byte[]> list) {
        this(status, map, -1, list);
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final long getThrottleEndTimeMillis() {
        return this.zzgmh;
    }

    public final byte[] zza(String str, byte[] bArr, String str2) {
        Map<String, TreeMap<String, byte[]>> map = this.zzgmg;
        boolean z = false;
        if (!(map == null || map.get(str2) == null || this.zzgmg.get(str2).get(str) == null)) {
            z = true;
        }
        if (z) {
            return (byte[]) this.zzgmg.get(str2).get(str);
        }
        return null;
    }

    public final List<byte[]> zzaol() {
        return this.zzgmi;
    }

    public final Map<String, Set<String>> zzaom() {
        HashMap hashMap = new HashMap();
        Map<String, TreeMap<String, byte[]>> map = this.zzgmg;
        if (map != null) {
            for (String next : map.keySet()) {
                Map map2 = this.zzgmg.get(next);
                if (map2 != null) {
                    hashMap.put(next, map2.keySet());
                }
            }
        }
        return hashMap;
    }
}

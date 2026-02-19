package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public final class zzfap {
    private long zzfol;
    private List<byte[]> zzgmi;
    private Map<String, Map<String, byte[]>> zzosp;

    public zzfap(Map<String, Map<String, byte[]>> map, long j, List<byte[]> list) {
        this.zzosp = map;
        this.zzfol = j;
        this.zzgmi = list;
    }

    public final long getTimestamp() {
        return this.zzfol;
    }

    public final void setTimestamp(long j) {
        this.zzfol = j;
    }

    public final List<byte[]> zzaol() {
        return this.zzgmi;
    }

    public final boolean zzbl(String str, String str2) {
        return zzcnm() && zzsk(str2) && zzbm(str, str2) != null;
    }

    public final byte[] zzbm(String str, String str2) {
        if (str == null || !zzsk(str2)) {
            return null;
        }
        return (byte[]) this.zzosp.get(str2).get(str);
    }

    public final Set<String> zzbn(String str, String str2) {
        TreeSet treeSet = new TreeSet();
        if (!zzsk(str2)) {
            return treeSet;
        }
        if (str == null || str.isEmpty()) {
            return this.zzosp.get(str2).keySet();
        }
        for (String str3 : this.zzosp.get(str2).keySet()) {
            if (str3.startsWith(str)) {
                treeSet.add(str3);
            }
        }
        return treeSet;
    }

    public final Map<String, Map<String, byte[]>> zzcnl() {
        return this.zzosp;
    }

    public final boolean zzcnm() {
        Map<String, Map<String, byte[]>> map = this.zzosp;
        return map != null && !map.isEmpty();
    }

    public final void zzi(Map<String, byte[]> map, String str) {
        if (this.zzosp == null) {
            this.zzosp = new HashMap();
        }
        this.zzosp.put(str, map);
    }

    public final boolean zzsk(String str) {
        return str != null && zzcnm() && this.zzosp.get(str) != null && !this.zzosp.get(str).isEmpty();
    }
}

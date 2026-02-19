package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzasy {
    private final Map<String, String> zzbwu;
    private final List<zzasf> zzedi;
    private final long zzedj;
    private final long zzedk;
    private final int zzedl;
    private final boolean zzedm;
    private final String zzedn;

    public zzasy(zzarh zzarh, Map<String, String> map, long j, boolean z) {
        this(zzarh, map, j, z, 0, 0, (List<zzasf>) null);
    }

    public zzasy(zzarh zzarh, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(zzarh, map, j, z, j2, i, (List<zzasf>) null);
    }

    public zzasy(zzarh zzarh, Map<String, String> map, long j, boolean z, long j2, int i, List<zzasf> list) {
        String zza;
        String zza2;
        zzbq.checkNotNull(zzarh);
        zzbq.checkNotNull(map);
        this.zzedk = j;
        this.zzedm = z;
        this.zzedj = j2;
        this.zzedl = i;
        this.zzedi = list != null ? list : Collections.emptyList();
        this.zzedn = zzt(list);
        HashMap hashMap = new HashMap();
        for (Map.Entry next : map.entrySet()) {
            if (zzn(next.getKey()) && (zza2 = zza(zzarh, next.getKey())) != null) {
                hashMap.put(zza2, zzb(zzarh, next.getValue()));
            }
        }
        for (Map.Entry next2 : map.entrySet()) {
            if (!zzn(next2.getKey()) && (zza = zza(zzarh, next2.getKey())) != null) {
                hashMap.put(zza, zzb(zzarh, next2.getValue()));
            }
        }
        if (!TextUtils.isEmpty(this.zzedn)) {
            zzatt.zzb((Map<String, String>) hashMap, "_v", this.zzedn);
            if (this.zzedn.equals("ma4.0.0") || this.zzedn.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzbwu = Collections.unmodifiableMap(hashMap);
    }

    private static String zza(zzarh zzarh, Object obj) {
        if (obj == null) {
            return null;
        }
        String obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            zzarh.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        if (TextUtils.isEmpty(obj2)) {
            return null;
        }
        return obj2;
    }

    private static String zzb(zzarh zzarh, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        String substring = obj2.substring(0, 8192);
        zzarh.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), substring);
        return substring;
    }

    private final String zzk(String str, String str2) {
        zzbq.zzgv(str);
        zzbq.checkArgument(!str.startsWith("&"), "Short param name required");
        String str3 = this.zzbwu.get(str);
        return str3 != null ? str3 : str2;
    }

    private static boolean zzn(Object obj) {
        if (obj == null) {
            return false;
        }
        return obj.toString().startsWith("&");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzt(java.util.List<com.google.android.gms.internal.zzasf> r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0024
            java.util.Iterator r4 = r4.iterator()
        L_0x0007:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L_0x0024
            java.lang.Object r1 = r4.next()
            com.google.android.gms.internal.zzasf r1 = (com.google.android.gms.internal.zzasf) r1
            java.lang.String r2 = r1.getId()
            java.lang.String r3 = "appendVersion"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0007
            java.lang.String r4 = r1.getValue()
            goto L_0x0025
        L_0x0024:
            r4 = r0
        L_0x0025:
            boolean r1 = android.text.TextUtils.isEmpty(r4)
            if (r1 == 0) goto L_0x002c
            return r0
        L_0x002c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzasy.zzt(java.util.List):java.lang.String");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ht=");
        sb.append(this.zzedk);
        if (this.zzedj != 0) {
            sb.append(", dbId=");
            sb.append(this.zzedj);
        }
        if (this.zzedl != 0) {
            sb.append(", appUID=");
            sb.append(this.zzedl);
        }
        ArrayList arrayList = new ArrayList(this.zzbwu.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            String str = (String) obj;
            sb.append(", ");
            sb.append(str);
            sb.append("=");
            sb.append(this.zzbwu.get(str));
        }
        return sb.toString();
    }

    public final int zzaal() {
        return this.zzedl;
    }

    public final long zzaam() {
        return this.zzedj;
    }

    public final long zzaan() {
        return this.zzedk;
    }

    public final List<zzasf> zzaao() {
        return this.zzedi;
    }

    public final boolean zzaap() {
        return this.zzedm;
    }

    public final long zzaaq() {
        return zzatt.zzeo(zzk("_s", "0"));
    }

    public final String zzaar() {
        return zzk("_m", "");
    }

    public final Map<String, String> zzjq() {
        return this.zzbwu;
    }
}

package com.google.android.gms.internal;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzcih extends zzcli {
    zzcih(zzckj zzckj) {
        super(zzckj);
    }

    private final Boolean zza(double d, zzcnu zzcnu) {
        try {
            return zza(new BigDecimal(d), zzcnu, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(long j, zzcnu zzcnu) {
        try {
            return zza(new BigDecimal(j), zzcnu, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(zzcns zzcns, String str, zzcoc[] zzcocArr, long j) {
        Boolean bool;
        String str2;
        Object obj;
        if (zzcns.zzjtb != null) {
            Boolean zza = zza(j, zzcns.zzjtb);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return false;
            }
        }
        HashSet hashSet = new HashSet();
        for (zzcnt zzcnt : zzcns.zzjsz) {
            if (TextUtils.isEmpty(zzcnt.zzjtg)) {
                zzayp().zzbaw().zzj("null or empty param name in filter. event", zzayk().zzjp(str));
                return null;
            }
            hashSet.add(zzcnt.zzjtg);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzcoc zzcoc : zzcocArr) {
            if (hashSet.contains(zzcoc.name)) {
                if (zzcoc.zzjum != null) {
                    str2 = zzcoc.name;
                    obj = zzcoc.zzjum;
                } else if (zzcoc.zzjsl != null) {
                    str2 = zzcoc.name;
                    obj = zzcoc.zzjsl;
                } else if (zzcoc.zzgim != null) {
                    str2 = zzcoc.name;
                    obj = zzcoc.zzgim;
                } else {
                    zzayp().zzbaw().zze("Unknown value for param. event, param", zzayk().zzjp(str), zzayk().zzjq(zzcoc.name));
                    return null;
                }
                arrayMap.put(str2, obj);
            }
        }
        for (zzcnt zzcnt2 : zzcns.zzjsz) {
            boolean equals = Boolean.TRUE.equals(zzcnt2.zzjtf);
            String str3 = zzcnt2.zzjtg;
            if (TextUtils.isEmpty(str3)) {
                zzayp().zzbaw().zzj("Event has empty param name. event", zzayk().zzjp(str));
                return null;
            }
            Object obj2 = arrayMap.get(str3);
            if (obj2 instanceof Long) {
                if (zzcnt2.zzjte == null) {
                    zzayp().zzbaw().zze("No number filter for long param. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                    return null;
                }
                Boolean zza2 = zza(((Long) obj2).longValue(), zzcnt2.zzjte);
                if (zza2 == null) {
                    return null;
                }
                if ((true ^ zza2.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof Double) {
                if (zzcnt2.zzjte == null) {
                    zzayp().zzbaw().zze("No number filter for double param. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                    return null;
                }
                Boolean zza3 = zza(((Double) obj2).doubleValue(), zzcnt2.zzjte);
                if (zza3 == null) {
                    return null;
                }
                if ((true ^ zza3.booleanValue()) ^ equals) {
                    return false;
                }
            } else if (obj2 instanceof String) {
                if (zzcnt2.zzjtd != null) {
                    bool = zza((String) obj2, zzcnt2.zzjtd);
                } else if (zzcnt2.zzjte != null) {
                    String str4 = (String) obj2;
                    if (zzcno.zzkr(str4)) {
                        bool = zza(str4, zzcnt2.zzjte);
                    } else {
                        zzayp().zzbaw().zze("Invalid param value for number filter. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                        return null;
                    }
                } else {
                    zzayp().zzbaw().zze("No filter for String param. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                    return null;
                }
                if (bool == null) {
                    return null;
                }
                if ((true ^ bool.booleanValue()) ^ equals) {
                    return false;
                }
            } else {
                zzcjj zzayp = zzayp();
                if (obj2 == null) {
                    zzayp.zzbba().zze("Missing param for filter. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                    return false;
                }
                zzayp.zzbaw().zze("Unknown param type. event, param", zzayk().zzjp(str), zzayk().zzjq(str3));
                return null;
            }
        }
        return true;
    }

    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        boolean startsWith;
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzayp().zzbaw().zzj("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                startsWith = str.startsWith(str2);
                break;
            case 3:
                startsWith = str.endsWith(str2);
                break;
            case 4:
                startsWith = str.contains(str2);
                break;
            case 5:
                startsWith = str.equals(str2);
                break;
            case 6:
                startsWith = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(startsWith);
    }

    private final Boolean zza(String str, zzcnu zzcnu) {
        if (!zzcno.zzkr(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzcnu, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(String str, zzcnw zzcnw) {
        List<String> list;
        zzbq.checkNotNull(zzcnw);
        if (str == null || zzcnw.zzjtp == null || zzcnw.zzjtp.intValue() == 0) {
            return null;
        }
        if (zzcnw.zzjtp.intValue() == 6) {
            if (zzcnw.zzjts == null || zzcnw.zzjts.length == 0) {
                return null;
            }
        } else if (zzcnw.zzjtq == null) {
            return null;
        }
        int intValue = zzcnw.zzjtp.intValue();
        boolean z = zzcnw.zzjtr != null && zzcnw.zzjtr.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzcnw.zzjtq : zzcnw.zzjtq.toUpperCase(Locale.ENGLISH);
        if (zzcnw.zzjts == null) {
            list = null;
        } else {
            String[] strArr = zzcnw.zzjts;
            if (z) {
                list = Arrays.asList(strArr);
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (String upperCase2 : strArr) {
                    arrayList2.add(upperCase2.toUpperCase(Locale.ENGLISH));
                }
                list = arrayList2;
            }
        }
        return zza(str, intValue, z, upperCase, list, intValue == 1 ? upperCase : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        if (r3 != null) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r10, com.google.android.gms.internal.zzcnu r11, double r12) {
        /*
            com.google.android.gms.common.internal.zzbq.checkNotNull(r11)
            java.lang.Integer r0 = r11.zzjth
            r1 = 0
            if (r0 == 0) goto L_0x00f3
            java.lang.Integer r0 = r11.zzjth
            int r0 = r0.intValue()
            if (r0 != 0) goto L_0x0012
            goto L_0x00f3
        L_0x0012:
            java.lang.Integer r0 = r11.zzjth
            int r0 = r0.intValue()
            r2 = 4
            if (r0 != r2) goto L_0x0024
            java.lang.String r0 = r11.zzjtk
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r11.zzjtl
            if (r0 != 0) goto L_0x0029
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.String r0 = r11.zzjtj
            if (r0 != 0) goto L_0x0029
            return r1
        L_0x0029:
            java.lang.Integer r0 = r11.zzjth
            int r0 = r0.intValue()
            java.lang.Integer r3 = r11.zzjth
            int r3 = r3.intValue()
            if (r3 != r2) goto L_0x005a
            java.lang.String r3 = r11.zzjtk
            boolean r3 = com.google.android.gms.internal.zzcno.zzkr(r3)
            if (r3 == 0) goto L_0x0059
            java.lang.String r3 = r11.zzjtl
            boolean r3 = com.google.android.gms.internal.zzcno.zzkr(r3)
            if (r3 != 0) goto L_0x0048
            goto L_0x0059
        L_0x0048:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r4 = r11.zzjtk     // Catch:{ NumberFormatException -> 0x0059 }
            r3.<init>(r4)     // Catch:{ NumberFormatException -> 0x0059 }
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r11 = r11.zzjtl     // Catch:{ NumberFormatException -> 0x0059 }
            r4.<init>(r11)     // Catch:{ NumberFormatException -> 0x0059 }
            r11 = r3
            r3 = r1
            goto L_0x006c
        L_0x0059:
            return r1
        L_0x005a:
            java.lang.String r3 = r11.zzjtj
            boolean r3 = com.google.android.gms.internal.zzcno.zzkr(r3)
            if (r3 != 0) goto L_0x0063
            return r1
        L_0x0063:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x00f3 }
            java.lang.String r11 = r11.zzjtj     // Catch:{ NumberFormatException -> 0x00f3 }
            r3.<init>(r11)     // Catch:{ NumberFormatException -> 0x00f3 }
            r11 = r1
            r4 = r11
        L_0x006c:
            if (r0 != r2) goto L_0x0071
            if (r11 != 0) goto L_0x0073
            return r1
        L_0x0071:
            if (r3 == 0) goto L_0x00f3
        L_0x0073:
            r5 = -1
            r6 = 0
            r7 = 1
            if (r0 == r7) goto L_0x00e7
            r8 = 2
            if (r0 == r8) goto L_0x00db
            r9 = 3
            if (r0 == r9) goto L_0x0093
            if (r0 == r2) goto L_0x0081
            goto L_0x00f3
        L_0x0081:
            int r11 = r10.compareTo(r11)
            if (r11 == r5) goto L_0x008e
            int r10 = r10.compareTo(r4)
            if (r10 == r7) goto L_0x008e
            r6 = 1
        L_0x008e:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            return r10
        L_0x0093:
            r0 = 0
            int r11 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r11 == 0) goto L_0x00cf
            java.math.BigDecimal r11 = new java.math.BigDecimal
            r11.<init>(r12)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r0.<init>(r8)
            java.math.BigDecimal r11 = r11.multiply(r0)
            java.math.BigDecimal r11 = r3.subtract(r11)
            int r11 = r10.compareTo(r11)
            if (r11 != r7) goto L_0x00ca
            java.math.BigDecimal r11 = new java.math.BigDecimal
            r11.<init>(r12)
            java.math.BigDecimal r12 = new java.math.BigDecimal
            r12.<init>(r8)
            java.math.BigDecimal r11 = r11.multiply(r12)
            java.math.BigDecimal r11 = r3.add(r11)
            int r10 = r10.compareTo(r11)
            if (r10 != r5) goto L_0x00ca
            r6 = 1
        L_0x00ca:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            return r10
        L_0x00cf:
            int r10 = r10.compareTo(r3)
            if (r10 != 0) goto L_0x00d6
            r6 = 1
        L_0x00d6:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            return r10
        L_0x00db:
            int r10 = r10.compareTo(r3)
            if (r10 != r7) goto L_0x00e2
            r6 = 1
        L_0x00e2:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            return r10
        L_0x00e7:
            int r10 = r10.compareTo(r3)
            if (r10 != r5) goto L_0x00ee
            r6 = 1
        L_0x00ee:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r6)
            return r10
        L_0x00f3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcih.zza(java.math.BigDecimal, com.google.android.gms.internal.zzcnu, double):java.lang.Boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v64, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v86, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v71, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v9, resolved type: java.lang.Long} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0331  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0382  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x03a8  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x03c9  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0822  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0825  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x082d  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0844  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x025a A[EDGE_INSN: B:273:0x025a->B:75:0x025a ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x025c  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0276  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcoa[] zza(java.lang.String r47, com.google.android.gms.internal.zzcob[] r48, com.google.android.gms.internal.zzcog[] r49) {
        /*
            r46 = this;
            r7 = r46
            r15 = r47
            r13 = r48
            r14 = r49
            com.google.android.gms.common.internal.zzbq.zzgv(r47)
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            androidx.collection.ArrayMap r12 = new androidx.collection.ArrayMap
            r12.<init>()
            androidx.collection.ArrayMap r10 = new androidx.collection.ArrayMap
            r10.<init>()
            androidx.collection.ArrayMap r9 = new androidx.collection.ArrayMap
            r9.<init>()
            com.google.android.gms.internal.zzcil r0 = r46.zzayj()
            java.util.Map r0 = r0.zzjm(r15)
            if (r0 == 0) goto L_0x00fa
            java.util.Set r1 = r0.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0031:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00fa
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            java.lang.Object r3 = r0.get(r3)
            com.google.android.gms.internal.zzcof r3 = (com.google.android.gms.internal.zzcof) r3
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.Object r4 = r10.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            java.lang.Object r5 = r9.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            if (r4 != 0) goto L_0x0079
            java.util.BitSet r4 = new java.util.BitSet
            r4.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r2)
            r10.put(r5, r4)
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r2)
            r9.put(r6, r5)
        L_0x0079:
            r6 = 0
        L_0x007a:
            long[] r8 = r3.zzjvo
            int r8 = r8.length
            int r8 = r8 << 6
            if (r6 >= r8) goto L_0x00c2
            long[] r8 = r3.zzjvo
            boolean r8 = com.google.android.gms.internal.zzcno.zza((long[]) r8, (int) r6)
            if (r8 == 0) goto L_0x00b3
            com.google.android.gms.internal.zzcjj r8 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r8 = r8.zzbba()
            r17 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            r18 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            r19 = r9
            java.lang.String r9 = "Filter already evaluated. audience ID, filter ID"
            r8.zze(r9, r0, r1)
            r5.set(r6)
            long[] r0 = r3.zzjvp
            boolean r0 = com.google.android.gms.internal.zzcno.zza((long[]) r0, (int) r6)
            if (r0 == 0) goto L_0x00b9
            r4.set(r6)
            goto L_0x00b9
        L_0x00b3:
            r17 = r0
            r18 = r1
            r19 = r9
        L_0x00b9:
            int r6 = r6 + 1
            r0 = r17
            r1 = r18
            r9 = r19
            goto L_0x007a
        L_0x00c2:
            r17 = r0
            r18 = r1
            r19 = r9
            com.google.android.gms.internal.zzcoa r0 = new com.google.android.gms.internal.zzcoa
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r12.put(r1, r0)
            r1 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r0.zzjug = r2
            r0.zzjuf = r3
            com.google.android.gms.internal.zzcof r1 = new com.google.android.gms.internal.zzcof
            r1.<init>()
            r0.zzjue = r1
            com.google.android.gms.internal.zzcof r1 = r0.zzjue
            long[] r2 = com.google.android.gms.internal.zzcno.zza((java.util.BitSet) r4)
            r1.zzjvp = r2
            com.google.android.gms.internal.zzcof r0 = r0.zzjue
            long[] r1 = com.google.android.gms.internal.zzcno.zza((java.util.BitSet) r5)
            r0.zzjvo = r1
            r0 = r17
            r1 = r18
            goto L_0x0031
        L_0x00fa:
            r19 = r9
            java.lang.String r22 = "null"
            java.lang.String r8 = "Filter definition"
            java.lang.String r4 = "Skipping failed audience ID"
            if (r13 == 0) goto L_0x05b4
            androidx.collection.ArrayMap r2 = new androidx.collection.ArrayMap
            r2.<init>()
            int r1 = r13.length
            r23 = 0
            r20 = r23
            r0 = 0
            r14 = 0
            r17 = 0
        L_0x0112:
            if (r14 >= r1) goto L_0x05a5
            r9 = r13[r14]
            java.lang.String r3 = r9.name
            r26 = r8
            com.google.android.gms.internal.zzcoc[] r8 = r9.zzjui
            com.google.android.gms.internal.zzcik r5 = r46.zzayr()
            com.google.android.gms.internal.zzcja<java.lang.Boolean> r6 = com.google.android.gms.internal.zzciz.zzjjv
            boolean r5 = r5.zzc(r15, r6)
            if (r5 == 0) goto L_0x030d
            r46.zzayl()
            java.lang.String r5 = "_eid"
            java.lang.Object r6 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r9, (java.lang.String) r5)
            java.lang.Long r6 = (java.lang.Long) r6
            if (r6 == 0) goto L_0x0138
            r29 = 1
            goto L_0x013a
        L_0x0138:
            r29 = 0
        L_0x013a:
            r30 = r1
            if (r29 == 0) goto L_0x0148
            java.lang.String r1 = "_ep"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0148
            r1 = 1
            goto L_0x0149
        L_0x0148:
            r1 = 0
        L_0x0149:
            if (r1 == 0) goto L_0x02ba
            r46.zzayl()
            java.lang.String r1 = "_en"
            java.lang.Object r1 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r9, (java.lang.String) r1)
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            boolean r1 = android.text.TextUtils.isEmpty(r3)
            if (r1 == 0) goto L_0x0178
            com.google.android.gms.internal.zzcjj r1 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()
            java.lang.String r3 = "Extra parameter without an event name. eventId"
            r1.zzj(r3, r6)
            r31 = r0
            r35 = r2
            r36 = r4
            r25 = r10
            r16 = 0
            r32 = 1
            goto L_0x02a6
        L_0x0178:
            if (r0 == 0) goto L_0x018b
            if (r17 == 0) goto L_0x018b
            long r31 = r6.longValue()
            long r33 = r17.longValue()
            int r1 = (r31 > r33 ? 1 : (r31 == r33 ? 0 : -1))
            if (r1 == 0) goto L_0x0189
            goto L_0x018b
        L_0x0189:
            r5 = r0
            goto L_0x01b5
        L_0x018b:
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            android.util.Pair r1 = r1.zzb((java.lang.String) r15, (java.lang.Long) r6)
            r31 = r0
            if (r1 == 0) goto L_0x028e
            java.lang.Object r0 = r1.first
            if (r0 != 0) goto L_0x019d
            goto L_0x028e
        L_0x019d:
            java.lang.Object r0 = r1.first
            com.google.android.gms.internal.zzcob r0 = (com.google.android.gms.internal.zzcob) r0
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r20 = r1.longValue()
            r46.zzayl()
            java.lang.Object r1 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r0, (java.lang.String) r5)
            r17 = r1
            java.lang.Long r17 = (java.lang.Long) r17
            goto L_0x0189
        L_0x01b5:
            r0 = 1
            long r20 = r20 - r0
            int r0 = (r20 > r23 ? 1 : (r20 == r23 ? 0 : -1))
            if (r0 > 0) goto L_0x020b
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            r1.zzwj()
            com.google.android.gms.internal.zzcjj r0 = r1.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbba()
            java.lang.String r6 = "Clearing complex main event info. appId"
            r0.zzj(r6, r15)
            android.database.sqlite.SQLiteDatabase r0 = r1.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01ea }
            java.lang.String r6 = "delete from main_event_params where app_id=?"
            r25 = r2
            r29 = r3
            r3 = 1
            java.lang.String[] r2 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x01e8 }
            r16 = 0
            r2[r16] = r15     // Catch:{ SQLiteException -> 0x01e6 }
            r0.execSQL(r6, r2)     // Catch:{ SQLiteException -> 0x01e6 }
            goto L_0x01ff
        L_0x01e6:
            r0 = move-exception
            goto L_0x01f2
        L_0x01e8:
            r0 = move-exception
            goto L_0x01f0
        L_0x01ea:
            r0 = move-exception
            r25 = r2
            r29 = r3
            r3 = 1
        L_0x01f0:
            r16 = 0
        L_0x01f2:
            com.google.android.gms.internal.zzcjj r1 = r1.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbau()
            java.lang.String r2 = "Error clearing complex main event"
            r1.zzj(r2, r0)
        L_0x01ff:
            r36 = r4
            r1 = r5
            r35 = r25
            r32 = 1
            r25 = r10
            r10 = r29
            goto L_0x022f
        L_0x020b:
            r25 = r2
            r29 = r3
            r3 = 1
            r16 = 0
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            r35 = r25
            r2 = r47
            r25 = r10
            r10 = r29
            r32 = 1
            r3 = r6
            r36 = r4
            r27 = r5
            r6 = 0
            r4 = r20
            r6 = r27
            r1.zza(r2, r3, r4, r6)
            r1 = r27
        L_0x022f:
            com.google.android.gms.internal.zzcoc[] r0 = r1.zzjui
            int r0 = r0.length
            int r2 = r8.length
            int r0 = r0 + r2
            com.google.android.gms.internal.zzcoc[] r2 = new com.google.android.gms.internal.zzcoc[r0]
            com.google.android.gms.internal.zzcoc[] r3 = r1.zzjui
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x023b:
            r27 = r1
            if (r5 >= r4) goto L_0x025a
            r1 = r3[r5]
            r46.zzayl()
            r28 = r3
            java.lang.String r3 = r1.name
            com.google.android.gms.internal.zzcoc r3 = com.google.android.gms.internal.zzcno.zza((com.google.android.gms.internal.zzcob) r9, (java.lang.String) r3)
            if (r3 != 0) goto L_0x0253
            int r3 = r6 + 1
            r2[r6] = r1
            r6 = r3
        L_0x0253:
            int r5 = r5 + 1
            r1 = r27
            r3 = r28
            goto L_0x023b
        L_0x025a:
            if (r6 <= 0) goto L_0x0276
            int r1 = r8.length
            r3 = 0
        L_0x025e:
            if (r3 >= r1) goto L_0x026a
            r4 = r8[r3]
            int r5 = r6 + 1
            r2[r6] = r4
            int r3 = r3 + 1
            r6 = r5
            goto L_0x025e
        L_0x026a:
            if (r6 != r0) goto L_0x026e
            r8 = r2
            goto L_0x0283
        L_0x026e:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r2, r6)
            com.google.android.gms.internal.zzcoc[] r0 = (com.google.android.gms.internal.zzcoc[]) r0
            r8 = r0
            goto L_0x0283
        L_0x0276:
            com.google.android.gms.internal.zzcjj r0 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r1 = "No unique parameters in main event. eventName"
            r0.zzj(r1, r10)
        L_0x0283:
            r5 = r10
            r28 = r17
            r33 = r20
            r0 = r27
            r27 = r8
            goto L_0x0325
        L_0x028e:
            r35 = r2
            r36 = r4
            r25 = r10
            r16 = 0
            r32 = 1
            r10 = r3
            com.google.android.gms.internal.zzcjj r0 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()
            java.lang.String r1 = "Extra parameter without existing main event. eventName, eventId"
            r0.zze(r1, r10, r6)
        L_0x02a6:
            r7 = r49
            r45 = r12
            r42 = r19
            r43 = r25
            r41 = r26
            r0 = r31
            r25 = r14
            r14 = r11
            r11 = r15
            r15 = r36
            goto L_0x058d
        L_0x02ba:
            r31 = r0
            r35 = r2
            r36 = r4
            r25 = r10
            r16 = 0
            r32 = 1
            if (r29 == 0) goto L_0x030b
            r46.zzayl()
            java.lang.Long r0 = java.lang.Long.valueOf(r23)
            java.lang.String r1 = "_epc"
            java.lang.Object r1 = com.google.android.gms.internal.zzcno.zzb((com.google.android.gms.internal.zzcob) r9, (java.lang.String) r1)
            if (r1 != 0) goto L_0x02d8
            goto L_0x02d9
        L_0x02d8:
            r0 = r1
        L_0x02d9:
            java.lang.Long r0 = (java.lang.Long) r0
            long r20 = r0.longValue()
            int r0 = (r20 > r23 ? 1 : (r20 == r23 ? 0 : -1))
            if (r0 > 0) goto L_0x02f3
            com.google.android.gms.internal.zzcjj r0 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r0 = r0.zzbaw()
            java.lang.String r1 = "Complex event with zero extra param count. eventName"
            r0.zzj(r1, r3)
            r0 = r3
            r10 = r6
            goto L_0x0302
        L_0x02f3:
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            r2 = r47
            r0 = r3
            r3 = r6
            r4 = r20
            r10 = r6
            r6 = r9
            r1.zza(r2, r3, r4, r6)
        L_0x0302:
            r5 = r0
            r27 = r8
            r0 = r9
            r28 = r10
            r33 = r20
            goto L_0x0325
        L_0x030b:
            r0 = r3
            goto L_0x031c
        L_0x030d:
            r31 = r0
            r30 = r1
            r35 = r2
            r0 = r3
            r36 = r4
            r25 = r10
            r16 = 0
            r32 = 1
        L_0x031c:
            r5 = r0
            r27 = r8
            r28 = r17
            r33 = r20
            r0 = r31
        L_0x0325:
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            java.lang.String r2 = r9.name
            com.google.android.gms.internal.zzcit r1 = r1.zzae(r15, r2)
            if (r1 != 0) goto L_0x0382
            com.google.android.gms.internal.zzcjj r1 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbaw()
            java.lang.Object r2 = com.google.android.gms.internal.zzcjj.zzjs(r47)
            com.google.android.gms.internal.zzcjh r3 = r46.zzayk()
            java.lang.String r3 = r3.zzjp(r5)
            java.lang.String r4 = "Event aggregate wasn't created during raw event logging. appId, event"
            r1.zze(r4, r2, r3)
            com.google.android.gms.internal.zzcit r1 = new com.google.android.gms.internal.zzcit
            java.lang.String r10 = r9.name
            r2 = 1
            r20 = 1
            java.lang.Long r4 = r9.zzjuj
            long r37 = r4.longValue()
            r39 = 0
            r4 = 0
            r6 = 0
            r29 = 0
            r41 = r26
            r9 = 0
            r8 = r1
            r42 = r19
            r9 = r47
            r43 = r25
            r44 = r11
            r45 = r12
            r11 = r2
            r3 = r49
            r25 = r14
            r13 = r20
            r2 = r15
            r15 = r37
            r17 = r39
            r19 = r4
            r20 = r6
            r21 = r29
            r8.<init>(r9, r10, r11, r13, r15, r17, r19, r20, r21)
            goto L_0x0395
        L_0x0382:
            r3 = r49
            r44 = r11
            r45 = r12
            r2 = r15
            r42 = r19
            r43 = r25
            r41 = r26
            r25 = r14
            com.google.android.gms.internal.zzcit r1 = r1.zzban()
        L_0x0395:
            com.google.android.gms.internal.zzcil r4 = r46.zzayj()
            r4.zza((com.google.android.gms.internal.zzcit) r1)
            long r8 = r1.zzjhs
            r10 = r35
            java.lang.Object r1 = r10.get(r5)
            java.util.Map r1 = (java.util.Map) r1
            if (r1 != 0) goto L_0x03ba
            com.google.android.gms.internal.zzcil r1 = r46.zzayj()
            java.util.Map r1 = r1.zzaj(r2, r5)
            if (r1 != 0) goto L_0x03b7
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap
            r1.<init>()
        L_0x03b7:
            r10.put(r5, r1)
        L_0x03ba:
            r11 = r1
            java.util.Set r1 = r11.keySet()
            java.util.Iterator r12 = r1.iterator()
        L_0x03c3:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x057f
            java.lang.Object r1 = r12.next()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r13 = r1.intValue()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r14 = r44
            boolean r1 = r14.contains(r1)
            if (r1 == 0) goto L_0x03f3
            com.google.android.gms.internal.zzcjj r1 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbba()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r15 = r36
            r1.zzj(r15, r4)
            r44 = r14
            goto L_0x03c3
        L_0x03f3:
            r15 = r36
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r6 = r45
            java.lang.Object r1 = r6.get(r1)
            com.google.android.gms.internal.zzcoa r1 = (com.google.android.gms.internal.zzcoa) r1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r35 = r10
            r10 = r43
            java.lang.Object r4 = r10.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            r16 = r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r13)
            r17 = r12
            r12 = r42
            java.lang.Object r0 = r12.get(r0)
            java.util.BitSet r0 = (java.util.BitSet) r0
            if (r1 != 0) goto L_0x044b
            com.google.android.gms.internal.zzcoa r0 = new com.google.android.gms.internal.zzcoa
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r6.put(r1, r0)
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r32)
            r0.zzjug = r1
            java.util.BitSet r4 = new java.util.BitSet
            r4.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r13)
            r10.put(r0, r4)
            java.util.BitSet r0 = new java.util.BitSet
            r0.<init>()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r12.put(r1, r0)
        L_0x044b:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            java.lang.Object r1 = r11.get(r1)
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r18 = r1.iterator()
        L_0x0459:
            boolean r1 = r18.hasNext()
            if (r1 == 0) goto L_0x056b
            java.lang.Object r1 = r18.next()
            com.google.android.gms.internal.zzcns r1 = (com.google.android.gms.internal.zzcns) r1
            com.google.android.gms.internal.zzcjj r2 = r46.zzayp()
            r19 = r11
            r11 = 2
            boolean r2 = r2.zzae(r11)
            if (r2 == 0) goto L_0x04a7
            com.google.android.gms.internal.zzcjj r2 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r2 = r2.zzbba()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r3 = r1.zzjsx
            r45 = r6
            com.google.android.gms.internal.zzcjh r6 = r46.zzayk()
            java.lang.String r7 = r1.zzjsy
            java.lang.String r6 = r6.zzjp(r7)
            java.lang.String r7 = "Evaluating filter. audience, filter, event"
            r2.zzd(r7, r11, r3, r6)
            com.google.android.gms.internal.zzcjj r2 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r2 = r2.zzbba()
            com.google.android.gms.internal.zzcjh r3 = r46.zzayk()
            java.lang.String r3 = r3.zza((com.google.android.gms.internal.zzcns) r1)
            r7 = r41
            r2.zzj(r7, r3)
            goto L_0x04ab
        L_0x04a7:
            r45 = r6
            r7 = r41
        L_0x04ab:
            java.lang.Integer r2 = r1.zzjsx
            if (r2 == 0) goto L_0x0535
            java.lang.Integer r2 = r1.zzjsx
            int r2 = r2.intValue()
            r11 = 256(0x100, float:3.59E-43)
            if (r2 <= r11) goto L_0x04bb
            goto L_0x0535
        L_0x04bb:
            java.lang.Integer r2 = r1.zzjsx
            int r2 = r2.intValue()
            boolean r2 = r4.get(r2)
            if (r2 == 0) goto L_0x04e4
            com.google.android.gms.internal.zzcjj r2 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r2 = r2.zzbba()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r1 = r1.zzjsx
            java.lang.String r6 = "Event filter already evaluated true. audience ID, filter ID"
            r2.zze(r6, r3, r1)
            r2 = r47
            r3 = r49
            r41 = r7
            r11 = r19
            goto L_0x0565
        L_0x04e4:
            r6 = r1
            r1 = r46
            r3 = r47
            r2 = r6
            r11 = r3
            r41 = r7
            r7 = r49
            r3 = r5
            r42 = r12
            r12 = r4
            r4 = r27
            r20 = r5
            r43 = r10
            r10 = r6
            r5 = r8
            java.lang.Boolean r1 = r1.zza(r2, r3, r4, r5)
            com.google.android.gms.internal.zzcjj r2 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r2 = r2.zzbba()
            if (r1 != 0) goto L_0x050c
            r3 = r22
            goto L_0x050d
        L_0x050c:
            r3 = r1
        L_0x050d:
            java.lang.String r4 = "Event filter result"
            r2.zzj(r4, r3)
            if (r1 != 0) goto L_0x051c
            java.lang.Integer r1 = java.lang.Integer.valueOf(r13)
            r14.add(r1)
            goto L_0x055a
        L_0x051c:
            java.lang.Integer r2 = r10.zzjsx
            int r2 = r2.intValue()
            r0.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x055a
            java.lang.Integer r1 = r10.zzjsx
            int r1 = r1.intValue()
            r12.set(r1)
            goto L_0x055a
        L_0x0535:
            r11 = r47
            r20 = r5
            r41 = r7
            r43 = r10
            r42 = r12
            r7 = r49
            r10 = r1
            r12 = r4
            com.google.android.gms.internal.zzcjj r1 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r1 = r1.zzbaw()
            java.lang.Object r2 = com.google.android.gms.internal.zzcjj.zzjs(r47)
            java.lang.Integer r3 = r10.zzjsx
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r4 = "Invalid event filter ID. appId, id"
            r1.zze(r4, r2, r3)
        L_0x055a:
            r3 = r7
            r2 = r11
            r4 = r12
            r11 = r19
            r5 = r20
            r12 = r42
            r10 = r43
        L_0x0565:
            r6 = r45
            r7 = r46
            goto L_0x0459
        L_0x056b:
            r7 = r46
            r45 = r6
            r43 = r10
            r42 = r12
            r44 = r14
            r36 = r15
            r0 = r16
            r12 = r17
            r10 = r35
            goto L_0x03c3
        L_0x057f:
            r16 = r0
            r11 = r2
            r7 = r3
            r35 = r10
            r15 = r36
            r14 = r44
            r17 = r28
            r20 = r33
        L_0x058d:
            int r1 = r25 + 1
            r7 = r46
            r13 = r48
            r4 = r15
            r2 = r35
            r8 = r41
            r19 = r42
            r10 = r43
            r12 = r45
            r15 = r11
            r11 = r14
            r14 = r1
            r1 = r30
            goto L_0x0112
        L_0x05a5:
            r7 = r49
            r41 = r8
            r43 = r10
            r14 = r11
            r45 = r12
            r11 = r15
            r42 = r19
            r32 = 1
            goto L_0x05c1
        L_0x05b4:
            r41 = r8
            r43 = r10
            r45 = r12
            r7 = r14
            r42 = r19
            r32 = 1
            r14 = r11
            r11 = r15
        L_0x05c1:
            r15 = r4
            if (r7 == 0) goto L_0x08be
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            int r1 = r7.length
            r8 = 0
        L_0x05cb:
            if (r8 >= r1) goto L_0x08be
            r2 = r7[r8]
            java.lang.String r3 = r2.name
            java.lang.Object r3 = r0.get(r3)
            java.util.Map r3 = (java.util.Map) r3
            if (r3 != 0) goto L_0x05ef
            com.google.android.gms.internal.zzcil r3 = r46.zzayj()
            java.lang.String r4 = r2.name
            java.util.Map r3 = r3.zzak(r11, r4)
            if (r3 != 0) goto L_0x05ea
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
        L_0x05ea:
            java.lang.String r4 = r2.name
            r0.put(r4, r3)
        L_0x05ef:
            java.util.Set r4 = r3.keySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x05f7:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x08a4
            java.lang.Object r5 = r4.next()
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            boolean r6 = r14.contains(r6)
            if (r6 == 0) goto L_0x0621
            com.google.android.gms.internal.zzcjj r6 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r6 = r6.zzbba()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r6.zzj(r15, r5)
            goto L_0x05f7
        L_0x0621:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            r9 = r45
            java.lang.Object r6 = r9.get(r6)
            com.google.android.gms.internal.zzcoa r6 = (com.google.android.gms.internal.zzcoa) r6
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            r12 = r43
            java.lang.Object r10 = r12.get(r10)
            java.util.BitSet r10 = (java.util.BitSet) r10
            java.lang.Integer r13 = java.lang.Integer.valueOf(r5)
            r16 = r1
            r1 = r42
            java.lang.Object r13 = r1.get(r13)
            java.util.BitSet r13 = (java.util.BitSet) r13
            if (r6 != 0) goto L_0x0673
            com.google.android.gms.internal.zzcoa r6 = new com.google.android.gms.internal.zzcoa
            r6.<init>()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            r9.put(r10, r6)
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r32)
            r6.zzjug = r10
            java.util.BitSet r10 = new java.util.BitSet
            r10.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            r12.put(r6, r10)
            java.util.BitSet r13 = new java.util.BitSet
            r13.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            r1.put(r6, r13)
        L_0x0673:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)
            java.lang.Object r6 = r3.get(r6)
            java.util.List r6 = (java.util.List) r6
            java.util.Iterator r6 = r6.iterator()
        L_0x0681:
            boolean r17 = r6.hasNext()
            if (r17 == 0) goto L_0x0892
            java.lang.Object r17 = r6.next()
            r48 = r0
            r0 = r17
            com.google.android.gms.internal.zzcnv r0 = (com.google.android.gms.internal.zzcnv) r0
            r17 = r3
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            r18 = r4
            r4 = 2
            boolean r3 = r3.zzae(r4)
            if (r3 == 0) goto L_0x06d7
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbba()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            r19 = r6
            java.lang.Integer r6 = r0.zzjsx
            com.google.android.gms.internal.zzcjh r7 = r46.zzayk()
            r36 = r15
            java.lang.String r15 = r0.zzjtn
            java.lang.String r7 = r7.zzjr(r15)
            java.lang.String r15 = "Evaluating filter. audience, filter, property"
            r3.zzd(r15, r4, r6, r7)
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbba()
            com.google.android.gms.internal.zzcjh r4 = r46.zzayk()
            java.lang.String r4 = r4.zza((com.google.android.gms.internal.zzcnv) r0)
            r6 = r41
            r3.zzj(r6, r4)
            goto L_0x06dd
        L_0x06d7:
            r19 = r6
            r36 = r15
            r6 = r41
        L_0x06dd:
            java.lang.Integer r3 = r0.zzjsx
            if (r3 == 0) goto L_0x085d
            java.lang.Integer r3 = r0.zzjsx
            int r3 = r3.intValue()
            r4 = 256(0x100, float:3.59E-43)
            if (r3 <= r4) goto L_0x06ed
            goto L_0x085d
        L_0x06ed:
            java.lang.Integer r3 = r0.zzjsx
            int r3 = r3.intValue()
            boolean r3 = r10.get(r3)
            if (r3 == 0) goto L_0x071c
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbba()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r0 = r0.zzjsx
            java.lang.String r15 = "Property filter already evaluated true. audience ID, filter ID"
            r3.zze(r15, r7, r0)
            r0 = r48
            r7 = r49
            r41 = r6
            r3 = r17
            r4 = r18
            r6 = r19
        L_0x0718:
            r15 = r36
            goto L_0x0681
        L_0x071c:
            com.google.android.gms.internal.zzcnt r3 = r0.zzjto
            if (r3 != 0) goto L_0x073e
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r7 = r46.zzayk()
            java.lang.String r15 = r2.name
            java.lang.String r7 = r7.zzjr(r15)
            java.lang.String r15 = "Missing property filter. property"
        L_0x0734:
            r3.zzj(r15, r7)
            r15 = r46
            r20 = r5
        L_0x073b:
            r5 = 0
            goto L_0x0818
        L_0x073e:
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            java.lang.Boolean r15 = r3.zzjtf
            boolean r7 = r7.equals(r15)
            java.lang.Long r15 = r2.zzjum
            if (r15 == 0) goto L_0x0779
            com.google.android.gms.internal.zzcnu r15 = r3.zzjte
            if (r15 != 0) goto L_0x0763
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r7 = r46.zzayk()
            java.lang.String r15 = r2.name
            java.lang.String r7 = r7.zzjr(r15)
            java.lang.String r15 = "No number filter for long property. property"
            goto L_0x0734
        L_0x0763:
            java.lang.Long r15 = r2.zzjum
            r20 = r5
            long r4 = r15.longValue()
            com.google.android.gms.internal.zzcnu r3 = r3.zzjte
            r15 = r46
            java.lang.Boolean r3 = r15.zza((long) r4, (com.google.android.gms.internal.zzcnu) r3)
        L_0x0773:
            java.lang.Boolean r5 = zza((java.lang.Boolean) r3, (boolean) r7)
            goto L_0x0818
        L_0x0779:
            r15 = r46
            r20 = r5
            java.lang.Double r4 = r2.zzjsl
            if (r4 == 0) goto L_0x07a8
            com.google.android.gms.internal.zzcnu r4 = r3.zzjte
            if (r4 != 0) goto L_0x079b
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r4 = r46.zzayk()
            java.lang.String r5 = r2.name
            java.lang.String r4 = r4.zzjr(r5)
            java.lang.String r5 = "No number filter for double property. property"
            goto L_0x0813
        L_0x079b:
            java.lang.Double r4 = r2.zzjsl
            double r4 = r4.doubleValue()
            com.google.android.gms.internal.zzcnu r3 = r3.zzjte
            java.lang.Boolean r3 = r15.zza((double) r4, (com.google.android.gms.internal.zzcnu) r3)
            goto L_0x0773
        L_0x07a8:
            java.lang.String r4 = r2.zzgim
            if (r4 == 0) goto L_0x07ff
            com.google.android.gms.internal.zzcnw r4 = r3.zzjtd
            if (r4 != 0) goto L_0x07f5
            com.google.android.gms.internal.zzcnu r4 = r3.zzjte
            if (r4 != 0) goto L_0x07c9
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r4 = r46.zzayk()
            java.lang.String r5 = r2.name
            java.lang.String r4 = r4.zzjr(r5)
            java.lang.String r5 = "No string or number filter defined. property"
            goto L_0x0813
        L_0x07c9:
            java.lang.String r4 = r2.zzgim
            boolean r4 = com.google.android.gms.internal.zzcno.zzkr(r4)
            if (r4 == 0) goto L_0x07da
            java.lang.String r4 = r2.zzgim
            com.google.android.gms.internal.zzcnu r3 = r3.zzjte
            java.lang.Boolean r3 = r15.zza((java.lang.String) r4, (com.google.android.gms.internal.zzcnu) r3)
            goto L_0x0773
        L_0x07da:
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r4 = r46.zzayk()
            java.lang.String r5 = r2.name
            java.lang.String r4 = r4.zzjr(r5)
            java.lang.String r5 = r2.zzgim
            java.lang.String r7 = "Invalid user property value for Numeric number filter. property, value"
            r3.zze(r7, r4, r5)
            goto L_0x073b
        L_0x07f5:
            java.lang.String r4 = r2.zzgim
            com.google.android.gms.internal.zzcnw r3 = r3.zzjtd
            java.lang.Boolean r3 = r15.zza((java.lang.String) r4, (com.google.android.gms.internal.zzcnw) r3)
            goto L_0x0773
        L_0x07ff:
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            com.google.android.gms.internal.zzcjh r4 = r46.zzayk()
            java.lang.String r5 = r2.name
            java.lang.String r4 = r4.zzjr(r5)
            java.lang.String r5 = "User property has no value, property"
        L_0x0813:
            r3.zzj(r5, r4)
            goto L_0x073b
        L_0x0818:
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbba()
            if (r5 != 0) goto L_0x0825
            r4 = r22
            goto L_0x0826
        L_0x0825:
            r4 = r5
        L_0x0826:
            java.lang.String r7 = "Property filter result"
            r3.zzj(r7, r4)
            if (r5 != 0) goto L_0x0844
            java.lang.Integer r0 = java.lang.Integer.valueOf(r20)
            r14.add(r0)
        L_0x0834:
            r0 = r48
            r7 = r49
            r41 = r6
            r3 = r17
            r4 = r18
            r6 = r19
            r5 = r20
            goto L_0x0718
        L_0x0844:
            java.lang.Integer r3 = r0.zzjsx
            int r3 = r3.intValue()
            r13.set(r3)
            boolean r3 = r5.booleanValue()
            if (r3 == 0) goto L_0x0834
            java.lang.Integer r0 = r0.zzjsx
            int r0 = r0.intValue()
            r10.set(r0)
            goto L_0x0834
        L_0x085d:
            r15 = r46
            r20 = r5
            com.google.android.gms.internal.zzcjj r3 = r46.zzayp()
            com.google.android.gms.internal.zzcjl r3 = r3.zzbaw()
            java.lang.Object r4 = com.google.android.gms.internal.zzcjj.zzjs(r47)
            java.lang.Integer r0 = r0.zzjsx
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r5 = "Invalid property filter ID. appId, id"
            r3.zze(r5, r4, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r20)
            r14.add(r0)
            r0 = r48
            r7 = r49
            r42 = r1
            r41 = r6
            r45 = r9
            r43 = r12
            r1 = r16
            r3 = r17
            r4 = r18
            goto L_0x08a0
        L_0x0892:
            r36 = r15
            r15 = r46
            r7 = r49
            r42 = r1
            r45 = r9
            r43 = r12
            r1 = r16
        L_0x08a0:
            r15 = r36
            goto L_0x05f7
        L_0x08a4:
            r48 = r0
            r16 = r1
            r36 = r15
            r6 = r41
            r1 = r42
            r12 = r43
            r9 = r45
            r15 = r46
            int r8 = r8 + 1
            r7 = r49
            r1 = r16
            r15 = r36
            goto L_0x05cb
        L_0x08be:
            r15 = r46
            r1 = r42
            r12 = r43
            r9 = r45
            int r0 = r12.size()
            com.google.android.gms.internal.zzcoa[] r2 = new com.google.android.gms.internal.zzcoa[r0]
            java.util.Set r0 = r12.keySet()
            java.util.Iterator r3 = r0.iterator()
            r8 = 0
        L_0x08d5:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x09c0
            java.lang.Object r0 = r3.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            boolean r4 = r14.contains(r4)
            if (r4 != 0) goto L_0x08d5
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
            java.lang.Object r4 = r9.get(r4)
            com.google.android.gms.internal.zzcoa r4 = (com.google.android.gms.internal.zzcoa) r4
            if (r4 != 0) goto L_0x0900
            com.google.android.gms.internal.zzcoa r4 = new com.google.android.gms.internal.zzcoa
            r4.<init>()
        L_0x0900:
            int r5 = r8 + 1
            r2[r8] = r4
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r4.zzjst = r6
            com.google.android.gms.internal.zzcof r6 = new com.google.android.gms.internal.zzcof
            r6.<init>()
            r4.zzjue = r6
            com.google.android.gms.internal.zzcof r6 = r4.zzjue
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.Object r7 = r12.get(r7)
            java.util.BitSet r7 = (java.util.BitSet) r7
            long[] r7 = com.google.android.gms.internal.zzcno.zza((java.util.BitSet) r7)
            r6.zzjvp = r7
            com.google.android.gms.internal.zzcof r6 = r4.zzjue
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.Object r7 = r1.get(r7)
            java.util.BitSet r7 = (java.util.BitSet) r7
            long[] r7 = com.google.android.gms.internal.zzcno.zza((java.util.BitSet) r7)
            r6.zzjvo = r7
            com.google.android.gms.internal.zzcil r6 = r46.zzayj()
            com.google.android.gms.internal.zzcof r4 = r4.zzjue
            r6.zzyk()
            r6.zzwj()
            com.google.android.gms.common.internal.zzbq.zzgv(r47)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r4)
            int r7 = r4.zzhs()     // Catch:{ IOException -> 0x09a9 }
            byte[] r8 = new byte[r7]     // Catch:{ IOException -> 0x09a9 }
            r10 = 0
            com.google.android.gms.internal.zzflk r7 = com.google.android.gms.internal.zzflk.zzp(r8, r10, r7)     // Catch:{ IOException -> 0x09a7 }
            r4.zza((com.google.android.gms.internal.zzflk) r7)     // Catch:{ IOException -> 0x09a7 }
            r7.zzcyx()     // Catch:{ IOException -> 0x09a7 }
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r7 = "app_id"
            r4.put(r7, r11)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r7 = "audience_id"
            r4.put(r7, r0)
            java.lang.String r0 = "current_results"
            r4.put(r0, r8)
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0996 }
            java.lang.String r7 = "audience_filter_values"
            r8 = 5
            r13 = 0
            long r7 = r0.insertWithOnConflict(r7, r13, r4, r8)     // Catch:{ SQLiteException -> 0x0994 }
            r16 = -1
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x09bd
            com.google.android.gms.internal.zzcjj r0 = r6.zzayp()     // Catch:{ SQLiteException -> 0x0994 }
            com.google.android.gms.internal.zzcjl r0 = r0.zzbau()     // Catch:{ SQLiteException -> 0x0994 }
            java.lang.String r4 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.internal.zzcjj.zzjs(r47)     // Catch:{ SQLiteException -> 0x0994 }
            r0.zzj(r4, r7)     // Catch:{ SQLiteException -> 0x0994 }
            goto L_0x09bd
        L_0x0994:
            r0 = move-exception
            goto L_0x0998
        L_0x0996:
            r0 = move-exception
            r13 = 0
        L_0x0998:
            com.google.android.gms.internal.zzcjj r4 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r47)
            java.lang.String r7 = "Error storing filter results. appId"
            goto L_0x09ba
        L_0x09a7:
            r0 = move-exception
            goto L_0x09ab
        L_0x09a9:
            r0 = move-exception
            r10 = 0
        L_0x09ab:
            r13 = 0
            com.google.android.gms.internal.zzcjj r4 = r6.zzayp()
            com.google.android.gms.internal.zzcjl r4 = r4.zzbau()
            java.lang.Object r6 = com.google.android.gms.internal.zzcjj.zzjs(r47)
            java.lang.String r7 = "Configuration loss. Failed to serialize filter results. appId"
        L_0x09ba:
            r4.zze(r7, r6, r0)
        L_0x09bd:
            r8 = r5
            goto L_0x08d5
        L_0x09c0:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r2, r8)
            com.google.android.gms.internal.zzcoa[] r0 = (com.google.android.gms.internal.zzcoa[]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcih.zza(java.lang.String, com.google.android.gms.internal.zzcob[], com.google.android.gms.internal.zzcog[]):com.google.android.gms.internal.zzcoa[]");
    }

    /* access modifiers changed from: protected */
    public final boolean zzazq() {
        return false;
    }
}

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzbh;
import com.google.android.gms.internal.zzbi;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.plus.PlusShare;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class zzcy extends zzbr {
    private static final String ID = zzbh.JOINER.toString();
    private static final String zzkpn = zzbi.ARG0.toString();
    private static final String zzkqf = zzbi.ITEM_SEPARATOR.toString();
    private static final String zzkqg = zzbi.KEY_VALUE_SEPARATOR.toString();
    private static final String zzkqh = zzbi.ESCAPE.toString();

    public zzcy() {
        super(ID, zzkpn);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [int, java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zza(java.lang.String r3, java.lang.Integer r4, java.util.Set<java.lang.Character> r5) {
        /*
            int[] r0 = com.google.android.gms.tagmanager.zzcz.zzkqi
            r1 = 1
            int r4 = r4 - r1
            r4 = r0[r4]
            if (r4 == r1) goto L_0x0042
            r0 = 2
            if (r4 == r0) goto L_0x000c
            return r3
        L_0x000c:
            java.lang.String r4 = "\\"
            java.lang.String r0 = "\\\\"
            java.lang.String r3 = r3.replace(r4, r0)
            java.util.Iterator r5 = r5.iterator()
        L_0x0018:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0041
            java.lang.Object r0 = r5.next()
            java.lang.Character r0 = (java.lang.Character) r0
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = java.lang.String.valueOf(r0)
            int r2 = r1.length()
            if (r2 == 0) goto L_0x0037
            java.lang.String r1 = r4.concat(r1)
            goto L_0x003c
        L_0x0037:
            java.lang.String r1 = new java.lang.String
            r1.<init>(r4)
        L_0x003c:
            java.lang.String r3 = r3.replace(r0, r1)
            goto L_0x0018
        L_0x0041:
            return r3
        L_0x0042:
            java.lang.String r3 = com.google.android.gms.tagmanager.zzgo.zzmm(r3)     // Catch:{ UnsupportedEncodingException -> 0x0047 }
            return r3
        L_0x0047:
            r4 = move-exception
            java.lang.String r5 = "Joiner: unsupported encoding"
            com.google.android.gms.tagmanager.zzdj.zzb(r5, r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcy.zza(java.lang.String, int, java.util.Set):java.lang.String");
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [int, java.lang.Integer] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(java.lang.StringBuilder r0, java.lang.String r1, java.lang.Integer r2, java.util.Set<java.lang.Character> r3) {
        /*
            java.lang.String r1 = zza(r1, r2, r3)
            r0.append(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzcy.zza(java.lang.StringBuilder, java.lang.String, int, java.util.Set):void");
    }

    private static void zza(Set<Character> set, String str) {
        for (int i = 0; i < str.length(); i++) {
            set.add(Character.valueOf(str.charAt(i)));
        }
    }

    public final boolean zzbfh() {
        return true;
    }

    public final zzbt zzx(Map<String, zzbt> map) {
        zzbt zzbt = map.get(zzkpn);
        if (zzbt == null) {
            return zzgk.zzbil();
        }
        zzbt zzbt2 = map.get(zzkqf);
        String zzd = zzbt2 != null ? zzgk.zzd(zzbt2) : "";
        zzbt zzbt3 = map.get(zzkqg);
        String zzd2 = zzbt3 != null ? zzgk.zzd(zzbt3) : "=";
        int i = zzda.zzkqj;
        zzbt zzbt4 = map.get(zzkqh);
        HashSet hashSet = null;
        if (zzbt4 != null) {
            String zzd3 = zzgk.zzd(zzbt4);
            if (PlusShare.KEY_CALL_TO_ACTION_URL.equals(zzd3)) {
                i = zzda.zzkqk;
            } else if ("backslash".equals(zzd3)) {
                i = zzda.zzkql;
                hashSet = new HashSet();
                zza(hashSet, zzd);
                zza(hashSet, zzd2);
                hashSet.remove('\\');
            } else {
                String valueOf = String.valueOf(zzd3);
                zzdj.e(valueOf.length() != 0 ? "Joiner: unsupported escape type: ".concat(valueOf) : new String("Joiner: unsupported escape type: "));
                return zzgk.zzbil();
            }
        }
        StringBuilder sb = new StringBuilder();
        int i2 = zzbt.type;
        if (i2 == 2) {
            zzbt[] zzbtArr = zzbt.zzyl;
            int length = zzbtArr.length;
            boolean z = true;
            int i3 = 0;
            while (i3 < length) {
                zzbt zzbt5 = zzbtArr[i3];
                if (!z) {
                    sb.append(zzd);
                }
                zza(sb, zzgk.zzd(zzbt5), i, hashSet);
                i3++;
                z = false;
            }
        } else if (i2 != 3) {
            zza(sb, zzgk.zzd(zzbt), i, hashSet);
        } else {
            for (int i4 = 0; i4 < zzbt.zzym.length; i4++) {
                if (i4 > 0) {
                    sb.append(zzd);
                }
                String zzd4 = zzgk.zzd(zzbt.zzym[i4]);
                String zzd5 = zzgk.zzd(zzbt.zzyn[i4]);
                zza(sb, zzd4, i, hashSet);
                sb.append(zzd2);
                zza(sb, zzd5, i, hashSet);
            }
        }
        return zzgk.zzam(sb.toString());
    }
}

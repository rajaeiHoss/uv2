package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.gmsg.zzt;
import java.util.Map;

@zzabh
public final class zzaoc implements zzt<zzann> {
    private zzany zzdow;

    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzahw.zzcz(sb.toString());
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(zzann zzann, Map<String, String> map) {
        /*
            r11 = this;
            com.google.android.gms.internal.zzann r12 = (com.google.android.gms.internal.zzann) r12
            com.google.android.gms.ads.internal.zzbt.zzff()
            java.lang.String r0 = "abort"
            boolean r0 = r13.containsKey(r0)
            if (r0 == 0) goto L_0x0021
            com.google.android.gms.internal.zzany r13 = r11.zzdow
            if (r13 == 0) goto L_0x0015
            r13.abort()
            return
        L_0x0015:
            boolean r12 = com.google.android.gms.internal.zzanx.zzb((com.google.android.gms.internal.zzann) r12)
            if (r12 != 0) goto L_0x0020
            java.lang.String r12 = "Precache abort but no precache task running."
            com.google.android.gms.internal.zzahw.zzcz(r12)
        L_0x0020:
            return
        L_0x0021:
            java.lang.String r0 = "src"
            java.lang.Object r0 = r13.get(r0)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x00c1
            com.google.android.gms.internal.zzany r1 = r11.zzdow
            if (r1 == 0) goto L_0x0035
            java.lang.String r12 = "Threadless precache task has already started."
        L_0x0031:
            com.google.android.gms.internal.zzahw.zzcz(r12)
            return
        L_0x0035:
            com.google.android.gms.internal.zzanv r1 = com.google.android.gms.internal.zzanx.zzc(r12)
            if (r1 == 0) goto L_0x003e
            java.lang.String r12 = "Precache task is already running."
            goto L_0x0031
        L_0x003e:
            com.google.android.gms.ads.internal.zzv r1 = r12.zzbo()
            if (r1 != 0) goto L_0x0047
            java.lang.String r12 = "Precache requires a dependency provider."
            goto L_0x0031
        L_0x0047:
            com.google.android.gms.internal.zzanm r1 = new com.google.android.gms.internal.zzanm
            java.lang.String r2 = "flags"
            java.lang.Object r2 = r13.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            r1.<init>(r2)
            java.lang.String r2 = "notifyBytes"
            java.lang.Integer r2 = zze(r13, r2)
            java.lang.String r3 = "notifyMillis"
            java.lang.Integer r3 = zze(r13, r3)
            java.lang.String r4 = "player"
            java.lang.Integer r4 = zze(r13, r4)
            r5 = 0
            if (r4 != 0) goto L_0x006d
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
        L_0x006d:
            int r4 = r4.intValue()
            com.google.android.gms.ads.internal.zzv r6 = r12.zzbo()
            com.google.android.gms.internal.zzaod r6 = r6.zzaok
            r7 = 0
            com.google.android.gms.internal.zzany r4 = r6.zza(r12, r4, r7, r1)
            r6 = 1
            if (r2 != 0) goto L_0x0084
            if (r3 == 0) goto L_0x0082
            goto L_0x0084
        L_0x0082:
            r7 = 0
            goto L_0x0085
        L_0x0084:
            r7 = 1
        L_0x0085:
            if (r7 == 0) goto L_0x00b8
            java.lang.String r1 = r1.zzdnm
            java.lang.String r7 = ","
            java.lang.String[] r1 = r1.split(r7)
            int r7 = r1.length
            r8 = 0
        L_0x0091:
            if (r8 >= r7) goto L_0x00aa
            r9 = r1[r8]
            java.lang.String r10 = "1"
            boolean r10 = r9.equals(r10)
            if (r10 != 0) goto L_0x00a7
            java.lang.String r10 = "2"
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x00a7
            r5 = 1
            goto L_0x00aa
        L_0x00a7:
            int r8 = r8 + 1
            goto L_0x0091
        L_0x00aa:
            if (r5 == 0) goto L_0x00b8
            if (r2 == 0) goto L_0x00b2
            r2.intValue()
            goto L_0x00b5
        L_0x00b2:
            r3.intValue()
        L_0x00b5:
            r11.zzdow = r4
            goto L_0x00cf
        L_0x00b8:
            com.google.android.gms.internal.zzanv r1 = new com.google.android.gms.internal.zzanv
            r1.<init>(r12, r4, r0)
            r1.zzns()
            goto L_0x00cf
        L_0x00c1:
            com.google.android.gms.internal.zzanv r12 = com.google.android.gms.internal.zzanx.zzc(r12)
            if (r12 != 0) goto L_0x00cf
            com.google.android.gms.internal.zzany r12 = r11.zzdow
            if (r12 != 0) goto L_0x00cf
            java.lang.String r12 = "Precache must specify a source."
            goto L_0x0031
        L_0x00cf:
            java.lang.String r12 = "minBufferMs"
            java.lang.Integer r12 = zze(r13, r12)
            if (r12 == 0) goto L_0x00da
            r12.intValue()
        L_0x00da:
            java.lang.String r12 = "maxBufferMs"
            java.lang.Integer r12 = zze(r13, r12)
            if (r12 == 0) goto L_0x00e5
            r12.intValue()
        L_0x00e5:
            java.lang.String r12 = "bufferForPlaybackMs"
            java.lang.Integer r12 = zze(r13, r12)
            if (r12 == 0) goto L_0x00f0
            r12.intValue()
        L_0x00f0:
            java.lang.String r12 = "bufferForPlaybackAfterRebufferMs"
            java.lang.Integer r12 = zze(r13, r12)
            if (r12 == 0) goto L_0x00fb
            r12.intValue()
        L_0x00fb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzaoc.zza(java.lang.Object, java.util.Map):void");
    }
}

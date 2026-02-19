package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class zzdhw extends zzdcr {
    private static final Set<String> zzlao = new HashSet(Arrays.asList(new String[]{"GET", "HEAD", "POST", "PUT"}));
    private final zzczy zzlan;

    public zzdhw(zzczy zzczy) {
        this.zzlan = zzczy;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzdjq<?> zza(com.google.android.gms.internal.zzdbb r13, com.google.android.gms.internal.zzdjq<?>... r14) {
        /*
            r12 = this;
            r13 = 1
            com.google.android.gms.common.internal.zzbq.checkArgument(r13)
            int r0 = r14.length
            r1 = 0
            if (r0 != r13) goto L_0x000a
            r0 = 1
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            com.google.android.gms.common.internal.zzbq.checkArgument(r0)
            r0 = r14[r1]
            boolean r0 = r0 instanceof com.google.android.gms.internal.zzdka
            com.google.android.gms.common.internal.zzbq.checkArgument(r0)
            r0 = r14[r1]
            java.lang.String r2 = "url"
            com.google.android.gms.internal.zzdjq r0 = r0.zznj(r2)
            boolean r2 = r0 instanceof com.google.android.gms.internal.zzdkc
            com.google.android.gms.common.internal.zzbq.checkArgument(r2)
            com.google.android.gms.internal.zzdkc r0 = (com.google.android.gms.internal.zzdkc) r0
            java.lang.Object r0 = r0.value()
            java.lang.String r0 = (java.lang.String) r0
            r2 = r14[r1]
            java.lang.String r3 = "method"
            com.google.android.gms.internal.zzdjq r2 = r2.zznj(r3)
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcz
            java.lang.String r4 = "GET"
            if (r2 != r3) goto L_0x003d
            com.google.android.gms.internal.zzdkc r2 = new com.google.android.gms.internal.zzdkc
            r2.<init>(r4)
        L_0x003d:
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzdkc
            com.google.android.gms.common.internal.zzbq.checkArgument(r3)
            com.google.android.gms.internal.zzdkc r2 = (com.google.android.gms.internal.zzdkc) r2
            java.lang.Object r2 = r2.value()
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            java.util.Set<java.lang.String> r2 = zzlao
            boolean r2 = r2.contains(r8)
            com.google.android.gms.common.internal.zzbq.checkArgument(r2)
            r2 = r14[r1]
            java.lang.String r3 = "uniqueId"
            com.google.android.gms.internal.zzdjq r2 = r2.zznj(r3)
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcz
            if (r2 == r3) goto L_0x006b
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcy
            if (r2 == r3) goto L_0x006b
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzdkc
            if (r3 == 0) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r3 = 0
            goto L_0x006c
        L_0x006b:
            r3 = 1
        L_0x006c:
            com.google.android.gms.common.internal.zzbq.checkArgument(r3)
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcz
            r5 = 0
            if (r2 == r3) goto L_0x0083
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcy
            if (r2 != r3) goto L_0x0079
            goto L_0x0083
        L_0x0079:
            com.google.android.gms.internal.zzdkc r2 = (com.google.android.gms.internal.zzdkc) r2
            java.lang.Object r2 = r2.value()
            java.lang.String r2 = (java.lang.String) r2
            r9 = r2
            goto L_0x0084
        L_0x0083:
            r9 = r5
        L_0x0084:
            r2 = r14[r1]
            java.lang.String r3 = "headers"
            com.google.android.gms.internal.zzdjq r2 = r2.zznj(r3)
            com.google.android.gms.internal.zzdjw r3 = com.google.android.gms.internal.zzdjw.zzlcz
            if (r2 == r3) goto L_0x0097
            boolean r3 = r2 instanceof com.google.android.gms.internal.zzdka
            if (r3 == 0) goto L_0x0095
            goto L_0x0097
        L_0x0095:
            r3 = 0
            goto L_0x0098
        L_0x0097:
            r3 = 1
        L_0x0098:
            com.google.android.gms.common.internal.zzbq.checkArgument(r3)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            com.google.android.gms.internal.zzdjw r6 = com.google.android.gms.internal.zzdjw.zzlcz
            if (r2 != r6) goto L_0x00a6
            r10 = r5
            goto L_0x00ed
        L_0x00a6:
            com.google.android.gms.internal.zzdka r2 = (com.google.android.gms.internal.zzdka) r2
            java.lang.Object r2 = r2.value()
            java.util.Map r2 = (java.util.Map) r2
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x00b6:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x00ec
            java.lang.Object r6 = r2.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.getValue()
            com.google.android.gms.internal.zzdjq r6 = (com.google.android.gms.internal.zzdjq) r6
            boolean r10 = r6 instanceof com.google.android.gms.internal.zzdkc
            if (r10 != 0) goto L_0x00e0
            java.lang.Object[] r6 = new java.lang.Object[r13]
            r6[r1] = r7
            java.lang.String r7 = "Ignore the non-string value of header key %s."
            java.lang.String r6 = java.lang.String.format(r7, r6)
            com.google.android.gms.internal.zzdal.zzcz(r6)
            goto L_0x00b6
        L_0x00e0:
            com.google.android.gms.internal.zzdkc r6 = (com.google.android.gms.internal.zzdkc) r6
            java.lang.Object r6 = r6.value()
            java.lang.String r6 = (java.lang.String) r6
            r3.put(r7, r6)
            goto L_0x00b6
        L_0x00ec:
            r10 = r3
        L_0x00ed:
            r14 = r14[r1]
            java.lang.String r2 = "body"
            com.google.android.gms.internal.zzdjq r14 = r14.zznj(r2)
            com.google.android.gms.internal.zzdjw r2 = com.google.android.gms.internal.zzdjw.zzlcz
            if (r14 == r2) goto L_0x0100
            boolean r2 = r14 instanceof com.google.android.gms.internal.zzdkc
            if (r2 == 0) goto L_0x00fe
            goto L_0x0100
        L_0x00fe:
            r2 = 0
            goto L_0x0101
        L_0x0100:
            r2 = 1
        L_0x0101:
            com.google.android.gms.common.internal.zzbq.checkArgument(r2)
            com.google.android.gms.internal.zzdjw r2 = com.google.android.gms.internal.zzdjw.zzlcz
            if (r14 != r2) goto L_0x0109
            goto L_0x0112
        L_0x0109:
            com.google.android.gms.internal.zzdkc r14 = (com.google.android.gms.internal.zzdkc) r14
            java.lang.Object r14 = r14.value()
            r5 = r14
            java.lang.String r5 = (java.lang.String) r5
        L_0x0112:
            r14 = r5
            boolean r2 = r8.equals(r4)
            r11 = 2
            if (r2 != 0) goto L_0x0122
            java.lang.String r2 = "HEAD"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0133
        L_0x0122:
            if (r14 == 0) goto L_0x0133
            java.lang.Object[] r2 = new java.lang.Object[r11]
            r2[r1] = r8
            r2[r13] = r14
            java.lang.String r3 = "Body of %s hit will be ignored: %s."
            java.lang.String r2 = java.lang.String.format(r3, r2)
            com.google.android.gms.internal.zzdal.zzcz(r2)
        L_0x0133:
            com.google.android.gms.internal.zzczy r2 = r12.zzlan
            r3 = r0
            r4 = r8
            r5 = r9
            r6 = r10
            r7 = r14
            r2.zza(r3, r4, r5, r6, r7)
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r1] = r0
            r2[r13] = r8
            r2[r11] = r9
            r13 = 3
            r2[r13] = r10
            r13 = 4
            r2[r13] = r14
            java.lang.String r13 = "QueueRequest:\n  url = %s,\n  method = %s,\n  uniqueId = %s,\n  headers = %s,\n  body = %s"
            java.lang.String r13 = java.lang.String.format(r13, r2)
            com.google.android.gms.internal.zzdal.v(r13)
            com.google.android.gms.internal.zzdjw r13 = com.google.android.gms.internal.zzdjw.zzlcz
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzdhw.zza(com.google.android.gms.internal.zzdbb, com.google.android.gms.internal.zzdjq[]):com.google.android.gms.internal.zzdjq");
    }
}

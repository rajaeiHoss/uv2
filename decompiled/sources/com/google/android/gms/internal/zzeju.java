package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public final class zzeju implements zzegs {
    private static zzeju zznjl = new zzeju();
    private HashMap<zzegr, List<zzegr>> zznjk = new HashMap<>();

    private zzeju() {
    }

    public static zzeju zzbzo() {
        return zznjl;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0037, code lost:
        r1 = r6.zza(com.google.android.gms.internal.zzelu.zzam(r6.zzbxy().zzbvh()));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.zzegr r6) {
        /*
            r5 = this;
            java.util.HashMap<com.google.android.gms.internal.zzegr, java.util.List<com.google.android.gms.internal.zzegr>> r0 = r5.zznjk
            monitor-enter(r0)
            java.util.HashMap<com.google.android.gms.internal.zzegr, java.util.List<com.google.android.gms.internal.zzegr>> r1 = r5.zznjk     // Catch:{ all -> 0x0071 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0071 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0071 }
            r2 = 0
            if (r1 == 0) goto L_0x002d
            r3 = 0
        L_0x000f:
            int r4 = r1.size()     // Catch:{ all -> 0x0071 }
            if (r3 >= r4) goto L_0x0022
            java.lang.Object r4 = r1.get(r3)     // Catch:{ all -> 0x0071 }
            if (r4 != r6) goto L_0x001f
            r1.remove(r3)     // Catch:{ all -> 0x0071 }
            goto L_0x0022
        L_0x001f:
            int r3 = r3 + 1
            goto L_0x000f
        L_0x0022:
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x002d
            java.util.HashMap<com.google.android.gms.internal.zzegr, java.util.List<com.google.android.gms.internal.zzegr>> r1 = r5.zznjk     // Catch:{ all -> 0x0071 }
            r1.remove(r6)     // Catch:{ all -> 0x0071 }
        L_0x002d:
            com.google.android.gms.internal.zzelu r1 = r6.zzbxy()     // Catch:{ all -> 0x0071 }
            boolean r1 = r1.isDefault()     // Catch:{ all -> 0x0071 }
            if (r1 != 0) goto L_0x006f
            com.google.android.gms.internal.zzelu r1 = r6.zzbxy()     // Catch:{ all -> 0x0071 }
            com.google.android.gms.internal.zzegu r1 = r1.zzbvh()     // Catch:{ all -> 0x0071 }
            com.google.android.gms.internal.zzelu r1 = com.google.android.gms.internal.zzelu.zzam(r1)     // Catch:{ all -> 0x0071 }
            com.google.android.gms.internal.zzegr r1 = r6.zza((com.google.android.gms.internal.zzelu) r1)     // Catch:{ all -> 0x0071 }
            java.util.HashMap<com.google.android.gms.internal.zzegr, java.util.List<com.google.android.gms.internal.zzegr>> r3 = r5.zznjk     // Catch:{ all -> 0x0071 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x0071 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x0071 }
            if (r3 == 0) goto L_0x006f
        L_0x0051:
            int r4 = r3.size()     // Catch:{ all -> 0x0071 }
            if (r2 >= r4) goto L_0x0064
            java.lang.Object r4 = r3.get(r2)     // Catch:{ all -> 0x0071 }
            if (r4 != r6) goto L_0x0061
            r3.remove(r2)     // Catch:{ all -> 0x0071 }
            goto L_0x0064
        L_0x0061:
            int r2 = r2 + 1
            goto L_0x0051
        L_0x0064:
            boolean r6 = r3.isEmpty()     // Catch:{ all -> 0x0071 }
            if (r6 == 0) goto L_0x006f
            java.util.HashMap<com.google.android.gms.internal.zzegr, java.util.List<com.google.android.gms.internal.zzegr>> r6 = r5.zznjk     // Catch:{ all -> 0x0071 }
            r6.remove(r1)     // Catch:{ all -> 0x0071 }
        L_0x006f:
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            return
        L_0x0071:
            r6 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0071 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzeju.zzd(com.google.android.gms.internal.zzegr):void");
    }

    public final void zzi(zzegr zzegr) {
        synchronized (this.zznjk) {
            List list = this.zznjk.get(zzegr);
            if (list == null) {
                list = new ArrayList();
                this.zznjk.put(zzegr, list);
            }
            list.add(zzegr);
            if (!zzegr.zzbxy().isDefault()) {
                zzegr zza = zzegr.zza(zzelu.zzam(zzegr.zzbxy().zzbvh()));
                List list2 = this.zznjk.get(zza);
                if (list2 == null) {
                    list2 = new ArrayList();
                    this.zznjk.put(zza, list2);
                }
                list2.add(zzegr);
            }
            zzegr.zzcv(true);
            zzegr.zza((zzegs) this);
        }
    }

    public final void zzj(zzegr zzegr) {
        synchronized (this.zznjk) {
            List list = this.zznjk.get(zzegr);
            if (list != null && !list.isEmpty()) {
                if (zzegr.zzbxy().isDefault()) {
                    HashSet hashSet = new HashSet();
                    for (int size = list.size() - 1; size >= 0; size--) {
                        zzegr zzegr2 = (zzegr) list.get(size);
                        if (!hashSet.contains(zzegr2.zzbxy())) {
                            hashSet.add(zzegr2.zzbxy());
                            zzegr2.zzbyl();
                        }
                    }
                } else {
                    ((zzegr) list.get(0)).zzbyl();
                }
            }
        }
    }
}

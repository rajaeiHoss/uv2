package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@zzabh
public final class zzzs extends zzzi {
    private final zzov zzanh;
    private zzwf zzanu;
    /* access modifiers changed from: private */
    public final zzaof zzcct;
    private zzvq zzcir;
    private zzvo zzcon;
    protected zzvw zzcoo;
    /* access modifiers changed from: private */
    public boolean zzcop;

    zzzs(Context context, zzahe zzahe, zzwf zzwf, zzzn zzzn, zzov zzov, zzaof zzaof) {
        super(context, zzahe, zzzn);
        this.zzanu = zzwf;
        this.zzcir = zzahe.zzdcj;
        this.zzanh = zzov;
        this.zzcct = zzaof;
    }

    private static String zzk(List<zzvw> list) {
        String str = "";
        if (list == null) {
            return str;
        }
        Iterator<zzvw> it = list.iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                return str.substring(0, Math.max(0, str.length() - 1));
            }
            zzvw next = it.next();
            if (!(next == null || next.zzcje == null || TextUtils.isEmpty(next.zzcje.zzche))) {
                String valueOf = String.valueOf(str);
                String str2 = next.zzcje.zzche;
                int i2 = next.zzcjd;
                if (i2 == -1) {
                    i = 4;
                } else if (i2 != 0) {
                    i = i2 != 1 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? 6 : 5 : 3 : 2 : 1;
                }
                long j = next.zzcjj;
                StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 33);
                sb.append(str2);
                sb.append(".");
                sb.append(i);
                sb.append(".");
                sb.append(j);
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 1 + String.valueOf(sb2).length());
                sb3.append(valueOf);
                sb3.append(sb2);
                sb3.append("_");
                str = sb3.toString();
            }
        }
    }

    public final void onStop() {
        synchronized (this.zzcoe) {
            super.onStop();
            zzvo zzvo = this.zzcon;
            if (zzvo != null) {
                zzvo.cancel();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.zzvo] */
    /* JADX WARNING: type inference failed for: r17v2, types: [com.google.android.gms.internal.zzwc] */
    /* JADX WARNING: type inference failed for: r4v5, types: [com.google.android.gms.internal.zzvz] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x00a3, code lost:
        r2 = r2.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zze(long r32) throws com.google.android.gms.internal.zzzl {
        /*
            r31 = this;
            r1 = r31
            java.lang.Object r2 = r1.zzcoe
            monitor-enter(r2)
            com.google.android.gms.internal.zzvq r0 = r1.zzcir     // Catch:{ all -> 0x0173 }
            int r0 = r0.zzcif     // Catch:{ all -> 0x0173 }
            r3 = -1
            if (r0 == r3) goto L_0x0043
            com.google.android.gms.internal.zzvz r0 = new com.google.android.gms.internal.zzvz     // Catch:{ all -> 0x0173 }
            android.content.Context r5 = r1.mContext     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzahe r3 = r1.zzcob     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacf r6 = r3.zzcvm     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzwf r7 = r1.zzanu     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzvq r8 = r1.zzcir     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r3 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            boolean r9 = r3.zzbid     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r3 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            boolean r10 = r3.zzbif     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r3 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            java.lang.String r11 = r3.zzcuh     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzny<java.lang.Long> r3 = com.google.android.gms.internal.zzoi.zzbrg     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzog r4 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0173 }
            java.lang.Object r3 = r4.zzd(r3)     // Catch:{ all -> 0x0173 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0173 }
            long r14 = r3.longValue()     // Catch:{ all -> 0x0173 }
            r16 = 2
            com.google.android.gms.internal.zzahe r3 = r1.zzcob     // Catch:{ all -> 0x0173 }
            boolean r3 = r3.zzdcv     // Catch:{ all -> 0x0173 }
            r4 = r0
            r12 = r32
            r17 = r3
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r14, r16, r17)     // Catch:{ all -> 0x0173 }
            goto L_0x008a
        L_0x0043:
            com.google.android.gms.internal.zzwc r0 = new com.google.android.gms.internal.zzwc     // Catch:{ all -> 0x0173 }
            android.content.Context r3 = r1.mContext     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzahe r4 = r1.zzcob     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacf r4 = r4.zzcvm     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzwf r5 = r1.zzanu     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzvq r6 = r1.zzcir     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r7 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            boolean r7 = r7.zzbid     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r8 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            boolean r8 = r8.zzbif     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzacj r9 = r1.zzcoc     // Catch:{ all -> 0x0173 }
            java.lang.String r9 = r9.zzcuh     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzny<java.lang.Long> r10 = com.google.android.gms.internal.zzoi.zzbrg     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzog r11 = com.google.android.gms.internal.zzlc.zzio()     // Catch:{ all -> 0x0173 }
            java.lang.Object r10 = r11.zzd(r10)     // Catch:{ all -> 0x0173 }
            java.lang.Long r10 = (java.lang.Long) r10     // Catch:{ all -> 0x0173 }
            long r27 = r10.longValue()     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzov r10 = r1.zzanh     // Catch:{ all -> 0x0173 }
            com.google.android.gms.internal.zzahe r11 = r1.zzcob     // Catch:{ all -> 0x0173 }
            boolean r11 = r11.zzdcv     // Catch:{ all -> 0x0173 }
            r17 = r0
            r18 = r3
            r19 = r4
            r20 = r5
            r21 = r6
            r22 = r7
            r23 = r8
            r24 = r9
            r25 = r32
            r29 = r10
            r30 = r11
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r27, r29, r30)     // Catch:{ all -> 0x0173 }
        L_0x008a:
            r1.zzcon = r0     // Catch:{ all -> 0x0173 }
            monitor-exit(r2)     // Catch:{ all -> 0x0173 }
            java.util.ArrayList r0 = new java.util.ArrayList
            com.google.android.gms.internal.zzvq r2 = r1.zzcir
            java.util.List<com.google.android.gms.internal.zzvp> r2 = r2.zzchv
            r0.<init>(r2)
            com.google.android.gms.internal.zzahe r2 = r1.zzcob
            com.google.android.gms.internal.zzacf r2 = r2.zzcvm
            com.google.android.gms.internal.zzkk r2 = r2.zzcrv
            android.os.Bundle r2 = r2.zzbhf
            java.lang.String r3 = "com.google.ads.mediation.admob.AdMobAdapter"
            r4 = 0
            if (r2 == 0) goto L_0x00b0
            android.os.Bundle r2 = r2.getBundle(r3)
            if (r2 == 0) goto L_0x00b0
            java.lang.String r5 = "_skipMediation"
            boolean r2 = r2.getBoolean(r5)
            goto L_0x00b1
        L_0x00b0:
            r2 = 0
        L_0x00b1:
            if (r2 == 0) goto L_0x00cf
            java.util.ListIterator r2 = r0.listIterator()
        L_0x00b7:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x00cf
            java.lang.Object r5 = r2.next()
            com.google.android.gms.internal.zzvp r5 = (com.google.android.gms.internal.zzvp) r5
            java.util.List<java.lang.String> r5 = r5.zzchd
            boolean r5 = r5.contains(r3)
            if (r5 != 0) goto L_0x00b7
            r2.remove()
            goto L_0x00b7
        L_0x00cf:
            com.google.android.gms.internal.zzvo r2 = r1.zzcon
            com.google.android.gms.internal.zzvw r0 = r2.zzh(r0)
            r1.zzcoo = r0
            int r0 = r0.zzcjd
            r2 = 1
            if (r0 == 0) goto L_0x0104
            if (r0 == r2) goto L_0x00fb
            com.google.android.gms.internal.zzzl r0 = new com.google.android.gms.internal.zzzl
            com.google.android.gms.internal.zzvw r2 = r1.zzcoo
            int r2 = r2.zzcjd
            r3 = 40
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Unexpected mediation result: "
            r5.append(r3)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r0.<init>(r2, r4)
            throw r0
        L_0x00fb:
            com.google.android.gms.internal.zzzl r0 = new com.google.android.gms.internal.zzzl
            java.lang.String r2 = "No fill from any mediation ad networks."
            r3 = 3
            r0.<init>(r2, r3)
            throw r0
        L_0x0104:
            com.google.android.gms.internal.zzvw r0 = r1.zzcoo
            com.google.android.gms.internal.zzvp r0 = r0.zzcje
            if (r0 == 0) goto L_0x0172
            com.google.android.gms.internal.zzvw r0 = r1.zzcoo
            com.google.android.gms.internal.zzvp r0 = r0.zzcje
            java.lang.String r0 = r0.zzcho
            if (r0 == 0) goto L_0x0172
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r0.<init>(r2)
            android.os.Handler r2 = com.google.android.gms.internal.zzaij.zzdfn
            com.google.android.gms.internal.zzzt r3 = new com.google.android.gms.internal.zzzt
            r3.<init>(r1, r0)
            r2.post(r3)
            r2 = 10
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x014c }
            r0.await(r2, r5)     // Catch:{ InterruptedException -> 0x014c }
            java.lang.Object r2 = r1.zzcoe
            monitor-enter(r2)
            boolean r0 = r1.zzcop     // Catch:{ all -> 0x0149 }
            if (r0 == 0) goto L_0x0141
            com.google.android.gms.internal.zzaof r0 = r1.zzcct     // Catch:{ all -> 0x0149 }
            boolean r0 = r0.isDestroyed()     // Catch:{ all -> 0x0149 }
            if (r0 != 0) goto L_0x0139
            monitor-exit(r2)     // Catch:{ all -> 0x0149 }
            return
        L_0x0139:
            com.google.android.gms.internal.zzzl r0 = new com.google.android.gms.internal.zzzl     // Catch:{ all -> 0x0149 }
            java.lang.String r3 = "Assets not loaded, web view is destroyed"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x0149 }
            throw r0     // Catch:{ all -> 0x0149 }
        L_0x0141:
            com.google.android.gms.internal.zzzl r0 = new com.google.android.gms.internal.zzzl     // Catch:{ all -> 0x0149 }
            java.lang.String r3 = "View could not be prepared"
            r0.<init>(r3, r4)     // Catch:{ all -> 0x0149 }
            throw r0     // Catch:{ all -> 0x0149 }
        L_0x0149:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0149 }
            throw r0
        L_0x014c:
            r0 = move-exception
            com.google.android.gms.internal.zzzl r2 = new com.google.android.gms.internal.zzzl
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r3 = java.lang.String.valueOf(r0)
            int r3 = r3.length()
            int r3 = r3 + 38
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            java.lang.String r3 = "Interrupted while waiting for latch : "
            r5.append(r3)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r2.<init>(r0, r4)
            throw r2
        L_0x0172:
            return
        L_0x0173:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0173 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzzs.zze(long):void");
    }

    /* access modifiers changed from: protected */
    public final zzahd zzy(int i) {
        zzacf zzacf = this.zzcob.zzcvm;
        zzkk zzkk = zzacf.zzcrv;
        zzaof zzaof = this.zzcct;
        List<String> list = this.zzcoc.zzchw;
        List<String> list2 = this.zzcoc.zzchx;
        List<String> list3 = this.zzcoc.zzctq;
        int i2 = this.zzcoc.orientation;
        long j = this.zzcoc.zzcic;
        String str = zzacf.zzcry;
        boolean z = this.zzcoc.zzcto;
        zzvw zzvw = this.zzcoo;
        zzvp zzvp = zzvw != null ? zzvw.zzcje : null;
        zzvw zzvw2 = this.zzcoo;
        zzwi zzwi = zzvw2 != null ? zzvw2.zzcjf : null;
        zzvw zzvw3 = this.zzcoo;
        String name = zzvw3 != null ? zzvw3.zzcjg : AdMobAdapter.class.getName();
        zzvq zzvq = this.zzcir;
        zzvw zzvw4 = this.zzcoo;
        zzvs zzvs = zzvw4 != null ? zzvw4.zzcjh : null;
        zzvp zzvp2 = zzvp;
        zzwi zzwi2 = zzwi;
        long j2 = this.zzcoc.zzctp;
        zzko zzko = this.zzcob.zzaud;
        long j3 = j2;
        long j4 = this.zzcoc.zzctn;
        long j5 = this.zzcob.zzdcn;
        long j6 = this.zzcoc.zzcts;
        String str2 = this.zzcoc.zzctt;
        JSONObject jSONObject = this.zzcob.zzdch;
        zzagd zzagd = this.zzcoc.zzcuc;
        List<String> list4 = this.zzcoc.zzcud;
        List<String> list5 = this.zzcoc.zzcue;
        zzvq zzvq2 = this.zzcir;
        boolean z2 = zzvq2 != null ? zzvq2.zzcih : false;
        zzacl zzacl = this.zzcoc.zzcug;
        zzvo zzvo = this.zzcon;
        zzacl zzacl2 = zzacl;
        return new zzahd(zzkk, zzaof, list, i, list2, list3, i2, j, str, z, zzvp2, zzwi2, name, zzvq, zzvs, j3, zzko, j4, j5, j6, str2, jSONObject, (zzpx) null, zzagd, list4, list5, z2, zzacl2, zzvo != null ? zzk(zzvo.zzmf()) : null, this.zzcoc.zzchz, this.zzcoc.zzcuj, this.zzcob.zzdcu, this.zzcoc.zzaqw, this.zzcob.zzdcv);
    }
}

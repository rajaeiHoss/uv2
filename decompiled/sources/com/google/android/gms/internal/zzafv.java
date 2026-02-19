package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@zzabh
public final class zzafv extends zzahs implements zzafu {
    private final Context mContext;
    private final Object mLock;
    private final zzahe zzcob;
    private final long zzdae;
    private final ArrayList<zzafl> zzdaq;
    private final List<zzafo> zzdar;
    private final HashSet<String> zzdas;
    private final zzaeo zzdat;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzafv(android.content.Context r8, com.google.android.gms.internal.zzahe r9, com.google.android.gms.internal.zzaeo r10) {
        /*
            r7 = this;
            com.google.android.gms.internal.zzny<java.lang.Long> r0 = com.google.android.gms.internal.zzoi.zzboz
            com.google.android.gms.internal.zzog r1 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r0 = r1.zzd(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r5 = r0.longValue()
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r1.<init>(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzafv.<init>(android.content.Context, com.google.android.gms.internal.zzahe, com.google.android.gms.internal.zzaeo):void");
    }

    private zzafv(Context context, zzahe zzahe, zzaeo zzaeo, long j) {
        this.zzdaq = new ArrayList<>();
        this.zzdar = new ArrayList();
        this.zzdas = new HashSet<>();
        this.mLock = new Object();
        this.mContext = context;
        this.zzcob = zzahe;
        this.zzdat = zzaeo;
        this.zzdae = j;
    }

    private final zzahd zza(int i, String str, zzvp zzvp) {
        zzvp zzvp2 = zzvp;
        zzkk zzkk = this.zzcob.zzcvm.zzcrv;
        return new zzahd(zzkk, (zzaof) null, this.zzcob.zzdcw.zzchw, i, this.zzcob.zzdcw.zzchx, this.zzcob.zzdcw.zzctq, this.zzcob.zzdcw.orientation, this.zzcob.zzdcw.zzcic, this.zzcob.zzcvm.zzcry, this.zzcob.zzdcw.zzcto, zzvp2, (zzwi) null, str, this.zzcob.zzdcj, (zzvs) null, this.zzcob.zzdcw.zzctp, this.zzcob.zzaud, this.zzcob.zzdcw.zzctn, this.zzcob.zzdcn, this.zzcob.zzdcw.zzcts, this.zzcob.zzdcw.zzctt, this.zzcob.zzdch, (zzpx) null, this.zzcob.zzdcw.zzcuc, this.zzcob.zzdcw.zzcud, this.zzcob.zzdcw.zzcue, this.zzcob.zzdcw.zzcuf, this.zzcob.zzdcw.zzcug, zzpb(), this.zzcob.zzdcw.zzchz, this.zzcob.zzdcw.zzcuj, this.zzcob.zzdcu, this.zzcob.zzdcw.zzaqw, this.zzcob.zzdcv);
    }

    private final String zzpb() {
        StringBuilder sb = new StringBuilder("");
        List<zzafo> list = this.zzdar;
        if (list == null) {
            return sb.toString();
        }
        Iterator<zzafo> it = list.iterator();
        while (true) {
            int i = 1;
            if (!it.hasNext()) {
                return sb.substring(0, Math.max(0, sb.length() - 1));
            }
            zzafo next = it.next();
            if (next != null && !TextUtils.isEmpty(next.zzche)) {
                String str = next.zzche;
                int i2 = next.errorCode;
                if (i2 != 3) {
                    i = i2 != 4 ? i2 != 5 ? i2 != 6 ? i2 != 7 ? 6 : 3 : 0 : 4 : 2;
                }
                long j = next.zzcjj;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 33);
                sb2.append(str);
                sb2.append(".");
                sb2.append(i);
                sb2.append(".");
                sb2.append(j);
                sb.append(String.valueOf(sb2.toString()).concat("_"));
            }
        }
    }

    public final void onStop() {
    }

    public final void zza(String str, int i) {
    }

    public final void zzbr(String str) {
        synchronized (this.mLock) {
            this.zzdas.add(str);
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:87:0x0158 */
    public final void zzdo() {
        /*
            r19 = this;
            r11 = r19
            com.google.android.gms.internal.zzahe r0 = r11.zzcob
            com.google.android.gms.internal.zzvq r0 = r0.zzdcj
            java.util.List<com.google.android.gms.internal.zzvp> r0 = r0.zzchv
            java.util.Iterator r12 = r0.iterator()
        L_0x000c:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x00b6
            java.lang.Object r0 = r12.next()
            r13 = r0
            com.google.android.gms.internal.zzvp r13 = (com.google.android.gms.internal.zzvp) r13
            java.lang.String r14 = r13.zzchk
            java.util.List<java.lang.String> r0 = r13.zzchd
            java.util.Iterator r15 = r0.iterator()
        L_0x0021:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x000c
            java.lang.Object r0 = r15.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0040
            java.lang.String r1 = "com.google.ads.mediation.customevent.CustomEventAdapter"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L_0x003e
            goto L_0x0040
        L_0x003e:
            r3 = r0
            goto L_0x004c
        L_0x0040:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00ae }
            r0.<init>(r14)     // Catch:{ JSONException -> 0x00ae }
            java.lang.String r1 = "class_name"
            java.lang.String r0 = r0.getString(r1)     // Catch:{ JSONException -> 0x00ae }
            goto L_0x003e
        L_0x004c:
            java.lang.Object r9 = r11.mLock
            monitor-enter(r9)
            com.google.android.gms.internal.zzaeo r0 = r11.zzdat     // Catch:{ all -> 0x00a7 }
            com.google.android.gms.internal.zzafy r7 = r0.zzbq(r3)     // Catch:{ all -> 0x00a7 }
            if (r7 == 0) goto L_0x0081
            com.google.android.gms.internal.zzaft r0 = r7.zzpd()     // Catch:{ all -> 0x00a7 }
            if (r0 == 0) goto L_0x0081
            com.google.android.gms.internal.zzwi r0 = r7.zzpc()     // Catch:{ all -> 0x00a7 }
            if (r0 != 0) goto L_0x0064
            goto L_0x0081
        L_0x0064:
            com.google.android.gms.internal.zzafl r0 = new com.google.android.gms.internal.zzafl     // Catch:{ all -> 0x00a7 }
            android.content.Context r2 = r11.mContext     // Catch:{ all -> 0x00a7 }
            com.google.android.gms.internal.zzahe r6 = r11.zzcob     // Catch:{ all -> 0x00a7 }
            long r4 = r11.zzdae     // Catch:{ all -> 0x00a7 }
            r1 = r0
            r16 = r4
            r4 = r14
            r5 = r13
            r8 = r19
            r18 = r9
            r9 = r16
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x00ac }
            java.util.ArrayList<com.google.android.gms.internal.zzafl> r1 = r11.zzdaq     // Catch:{ all -> 0x00ac }
            r1.add(r0)     // Catch:{ all -> 0x00ac }
        L_0x007f:
            monitor-exit(r18)     // Catch:{ all -> 0x00ac }
            goto L_0x0021
        L_0x0081:
            r18 = r9
            java.util.List<com.google.android.gms.internal.zzafo> r0 = r11.zzdar     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.zzafq r1 = new com.google.android.gms.internal.zzafq     // Catch:{ all -> 0x00ac }
            r1.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r2 = r13.zzche     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.zzafq r1 = r1.zzbt(r2)     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.zzafq r1 = r1.zzbs(r3)     // Catch:{ all -> 0x00ac }
            r2 = 0
            com.google.android.gms.internal.zzafq r1 = r1.zzg(r2)     // Catch:{ all -> 0x00ac }
            r2 = 7
            com.google.android.gms.internal.zzafq r1 = r1.zzab(r2)     // Catch:{ all -> 0x00ac }
            com.google.android.gms.internal.zzafo r1 = r1.zzpa()     // Catch:{ all -> 0x00ac }
            r0.add(r1)     // Catch:{ all -> 0x00ac }
            goto L_0x007f
        L_0x00a7:
            r0 = move-exception
            r18 = r9
        L_0x00aa:
            monitor-exit(r18)     // Catch:{ all -> 0x00ac }
            throw r0
        L_0x00ac:
            r0 = move-exception
            goto L_0x00aa
        L_0x00ae:
            r0 = move-exception
            java.lang.String r1 = "Unable to determine custom event class name, skipping..."
            com.google.android.gms.internal.zzahw.zzb(r1, r0)
            goto L_0x0021
        L_0x00b6:
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            java.util.ArrayList<com.google.android.gms.internal.zzafl> r1 = r11.zzdaq
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
            r3 = 0
            r4 = 0
        L_0x00c5:
            if (r4 >= r2) goto L_0x00db
            java.lang.Object r5 = r1.get(r4)
            int r4 = r4 + 1
            com.google.android.gms.internal.zzafl r5 = (com.google.android.gms.internal.zzafl) r5
            java.lang.String r6 = r5.zzcip
            boolean r6 = r0.add(r6)
            if (r6 == 0) goto L_0x00c5
            r5.zzow()
            goto L_0x00c5
        L_0x00db:
            java.util.ArrayList<com.google.android.gms.internal.zzafl> r0 = r11.zzdaq
            r1 = r0
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r2 = r1.size()
        L_0x00e4:
            if (r3 >= r2) goto L_0x0191
            java.lang.Object r0 = r1.get(r3)
            int r3 = r3 + 1
            r4 = r0
            com.google.android.gms.internal.zzafl r4 = (com.google.android.gms.internal.zzafl) r4
            java.util.concurrent.Future r0 = r4.zzow()     // Catch:{ InterruptedException -> 0x0158, Exception -> 0x0139 }
            r0.get()     // Catch:{ InterruptedException -> 0x0158, Exception -> 0x0139 }
            java.lang.Object r5 = r11.mLock
            monitor-enter(r5)
            java.lang.String r0 = r4.zzcip     // Catch:{ all -> 0x0134 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0134 }
            if (r0 != 0) goto L_0x010a
            java.util.List<com.google.android.gms.internal.zzafo> r0 = r11.zzdar     // Catch:{ all -> 0x0134 }
            com.google.android.gms.internal.zzafo r6 = r4.zzox()     // Catch:{ all -> 0x0134 }
            r0.add(r6)     // Catch:{ all -> 0x0134 }
        L_0x010a:
            monitor-exit(r5)     // Catch:{ all -> 0x0134 }
            java.lang.Object r6 = r11.mLock
            monitor-enter(r6)
            java.util.HashSet<java.lang.String> r0 = r11.zzdas     // Catch:{ all -> 0x0131 }
            java.lang.String r5 = r4.zzcip     // Catch:{ all -> 0x0131 }
            boolean r0 = r0.contains(r5)     // Catch:{ all -> 0x0131 }
            if (r0 == 0) goto L_0x012f
            java.lang.String r0 = r4.zzcip     // Catch:{ all -> 0x0131 }
            com.google.android.gms.internal.zzvp r1 = r4.zzoy()     // Catch:{ all -> 0x0131 }
            r2 = -2
            com.google.android.gms.internal.zzahd r0 = r11.zza(r2, r0, r1)     // Catch:{ all -> 0x0131 }
            android.os.Handler r1 = com.google.android.gms.internal.zzako.zzaju     // Catch:{ all -> 0x0131 }
            com.google.android.gms.internal.zzafw r2 = new com.google.android.gms.internal.zzafw     // Catch:{ all -> 0x0131 }
            r2.<init>(r11, r0)     // Catch:{ all -> 0x0131 }
            r1.post(r2)     // Catch:{ all -> 0x0131 }
            monitor-exit(r6)     // Catch:{ all -> 0x0131 }
            return
        L_0x012f:
            monitor-exit(r6)     // Catch:{ all -> 0x0131 }
            goto L_0x00e4
        L_0x0131:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0131 }
            throw r0
        L_0x0134:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0134 }
            throw r0
        L_0x0137:
            r0 = move-exception
            goto L_0x0178
        L_0x0139:
            r0 = move-exception
            java.lang.String r5 = "Unable to resolve rewarded adapter."
            com.google.android.gms.internal.zzahw.zzc(r5, r0)     // Catch:{ all -> 0x0137 }
            java.lang.Object r5 = r11.mLock
            monitor-enter(r5)
            java.lang.String r0 = r4.zzcip     // Catch:{ all -> 0x0155 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0155 }
            if (r0 != 0) goto L_0x0153
            java.util.List<com.google.android.gms.internal.zzafo> r0 = r11.zzdar     // Catch:{ all -> 0x0155 }
            com.google.android.gms.internal.zzafo r4 = r4.zzox()     // Catch:{ all -> 0x0155 }
            r0.add(r4)     // Catch:{ all -> 0x0155 }
        L_0x0153:
            monitor-exit(r5)     // Catch:{ all -> 0x0155 }
            goto L_0x00e4
        L_0x0155:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0155 }
            throw r0
        L_0x0158:
            java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0137 }
            r0.interrupt()     // Catch:{ all -> 0x0137 }
            java.lang.Object r1 = r11.mLock
            monitor-enter(r1)
            java.lang.String r0 = r4.zzcip     // Catch:{ all -> 0x0175 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0175 }
            if (r0 != 0) goto L_0x0173
            java.util.List<com.google.android.gms.internal.zzafo> r0 = r11.zzdar     // Catch:{ all -> 0x0175 }
            com.google.android.gms.internal.zzafo r2 = r4.zzox()     // Catch:{ all -> 0x0175 }
            r0.add(r2)     // Catch:{ all -> 0x0175 }
        L_0x0173:
            monitor-exit(r1)     // Catch:{ all -> 0x0175 }
            goto L_0x0191
        L_0x0175:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0175 }
            throw r0
        L_0x0178:
            java.lang.Object r1 = r11.mLock
            monitor-enter(r1)
            java.lang.String r2 = r4.zzcip     // Catch:{ all -> 0x018e }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x018e }
            if (r2 != 0) goto L_0x018c
            java.util.List<com.google.android.gms.internal.zzafo> r2 = r11.zzdar     // Catch:{ all -> 0x018e }
            com.google.android.gms.internal.zzafo r3 = r4.zzox()     // Catch:{ all -> 0x018e }
            r2.add(r3)     // Catch:{ all -> 0x018e }
        L_0x018c:
            monitor-exit(r1)     // Catch:{ all -> 0x018e }
            throw r0
        L_0x018e:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x018e }
            throw r0
        L_0x0191:
            r0 = 3
            r1 = 0
            com.google.android.gms.internal.zzahd r0 = r11.zza(r0, r1, r1)
            android.os.Handler r1 = com.google.android.gms.internal.zzako.zzaju
            com.google.android.gms.internal.zzafx r2 = new com.google.android.gms.internal.zzafx
            r2.<init>(r11, r0)
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzafv.zzdo():void");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzl(zzahd zzahd) {
        this.zzdat.zzoq().zzb(zzahd);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(zzahd zzahd) {
        this.zzdat.zzoq().zzb(zzahd);
    }
}

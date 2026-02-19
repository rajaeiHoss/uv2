package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzekp {
    private static final zzela<Map<zzelr, zzeko>> zznkt = new zzekq();
    private static final zzela<Map<zzelr, zzeko>> zznku = new zzekr();
    /* access modifiers changed from: private */
    public static final zzela<zzeko> zznkv = new zzeks();
    private static final zzela<zzeko> zznkw = new zzekt();
    private final zzemm zzmxz;
    private final zzekj zznkg;
    private zzekw<Map<zzelr, zzeko>> zznkx;
    private final zzeos zznky;
    private long zznkz = 0;

    public zzekp(zzekj zzekj, zzemm zzemm, zzeos zzeos) {
        this.zznkg = zzekj;
        this.zzmxz = zzemm;
        this.zznky = zzeos;
        this.zznkx = new zzekw<>(null);
        try {
            zzekj.beginTransaction();
            zzekj.zzbo(zzeos.millis());
            zzekj.setTransactionSuccessful();
            zzekj.endTransaction();
            for (zzeko next : zzekj.zzbvm()) {
                this.zznkz = Math.max(next.id + 1, this.zznkz);
                zzb(next);
            }
        } catch (Throwable th) {
            this.zznkg.endTransaction();
            throw th;
        }
    }

    private final List<zzeko> zza(zzela<zzeko> zzela) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<zzegu, Map<zzelr, zzeko>>> it = this.zznkx.iterator();
        while (it.hasNext()) {
            for (zzeko zzeko : it.next().getValue().values()) {
                if (zzela.zzbw(zzeko)) {
                    arrayList.add(zzeko);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zza(zzeko zzeko) {
        zzb(zzeko);
        this.zznkg.zza(zzeko);
    }

    private final boolean zzad(zzegu zzegu) {
        return this.zznkx.zza(zzegu, zznkt) != null;
    }

    private final Set<Long> zzae(zzegu zzegu) {
        HashSet hashSet = new HashSet();
        Map<zzelr, zzeko> zzaj = this.zznkx.zzaj(zzegu);
        if (zzaj != null) {
            for (zzeko zzeko : zzaj.values()) {
                if (!zzeko.zznkr.zzcbe()) {
                    hashSet.add(Long.valueOf(zzeko.id));
                }
            }
        }
        return hashSet;
    }

    private final void zzb(zzeko zzeko) {
        zzelu zzelu = zzeko.zznkr;
        boolean z = false;
        zzepd.zzb(!zzelu.zzcbe() || zzelu.isDefault(), "Can't have tracked non-default query that loads all data");
        Map zzaj = this.zznkx.zzaj(zzeko.zznkr.zzbvh());
        if (zzaj == null) {
            zzaj = new HashMap();
            this.zznkx = this.zznkx.zzb(zzeko.zznkr.zzbvh(), zzaj);
        }
        zzeko zzeko2 = (zzeko) zzaj.get(zzeko.zznkr.zzcbh());
        if (zzeko2 == null || zzeko2.id == zzeko.id) {
            z = true;
        }
        zzepd.zzcw(z);
        zzaj.put(zzeko.zznkr.zzcbh(), zzeko);
    }

    private final void zzb(zzelu zzelu, boolean z) {
        zzeko zzeko;
        zzelu zzj = zzj(zzelu);
        zzeko zzk = zzk(zzj);
        long millis = this.zznky.millis();
        if (zzk != null) {
            zzeko zzeko2 = new zzeko(zzk.id, zzk.zznkr, millis, zzk.complete, zzk.zzjgp);
            zzeko = new zzeko(zzeko2.id, zzeko2.zznkr, zzeko2.zznks, zzeko2.complete, z);
        } else {
            long j = this.zznkz;
            this.zznkz = 1 + j;
            zzeko = new zzeko(j, zzj, millis, false, z);
        }
        zza(zzeko);
    }

    private static zzelu zzj(zzelu zzelu) {
        return zzelu.zzcbe() ? zzelu.zzam(zzelu.zzbvh()) : zzelu;
    }

    public final zzekk zza(zzekd zzekd) {
        List<zzeko> zza = zza(zznkv);
        long size = (long) zza.size();
        long min = size - Math.min((long) Math.floor((double) (((float) size) * (1.0f - zzekd.zzbzy()))), zzekd.zzbzz());
        zzekk zzekk = new zzekk();
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            int size2 = zza.size();
            StringBuilder sb = new StringBuilder(80);
            sb.append("Pruning old queries.  Prunable: ");
            sb.append(size2);
            sb.append(" Count to prune: ");
            sb.append(min);
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        Collections.sort(zza, new zzekv(this));
        for (int i = 0; ((long) i) < min; i++) {
            zzeko zzeko = zza.get(i);
            zzekk = zzekk.zzx(zzeko.zznkr.zzbvh());
            zzelu zzj = zzj(zzeko.zznkr);
            this.zznkg.zzbn(zzk(zzj).id);
            Map zzaj = this.zznkx.zzaj(zzj.zzbvh());
            zzaj.remove(zzj.zzcbh());
            if (zzaj.isEmpty()) {
                this.zznkx = this.zznkx.zzai(zzj.zzbvh());
            }
        }
        for (int i2 = (int) min; i2 < zza.size(); i2++) {
            zzekk = zzekk.zzy(zza.get(i2).zznkr.zzbvh());
        }
        List<zzeko> zza2 = zza(zznkw);
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm2 = this.zzmxz;
            int size3 = zza2.size();
            StringBuilder sb2 = new StringBuilder(31);
            sb2.append("Unprunable queries: ");
            sb2.append(size3);
            zzemm2.zzb(sb2.toString(), (Throwable) null, new Object[0]);
        }
        for (zzeko zzeko2 : zza2) {
            zzekk = zzekk.zzy(zzeko2.zznkr.zzbvh());
        }
        return zzekk;
    }

    public final Set<zzemq> zzaa(zzegu zzegu) {
        HashSet hashSet = new HashSet();
        Set<Long> zzae = zzae(zzegu);
        if (!zzae.isEmpty()) {
            hashSet.addAll(this.zznkg.zzg(zzae));
        }
        Iterator<Map.Entry<zzemq, zzekw<Map<zzelr, zzeko>>>> it = this.zznkx.zzah(zzegu).zzcag().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            zzemq zzemq = (zzemq) next.getKey();
            zzekw zzekw = (zzekw) next.getValue();
            if (zzekw.getValue() != null && zznkt.zzbw((Map) zzekw.getValue())) {
                hashSet.add(zzemq);
            }
        }
        return hashSet;
    }

    public final void zzab(zzegu zzegu) {
        zzeko zzeko;
        if (!zzad(zzegu)) {
            zzelu zzam = zzelu.zzam(zzegu);
            zzeko zzk = zzk(zzam);
            if (zzk == null) {
                long j = this.zznkz;
                this.zznkz = 1 + j;
                zzeko = new zzeko(j, zzam, this.zznky.millis(), true, false);
            } else {
                zzeko = zzk.zzcac();
            }
            zza(zzeko);
        }
    }

    public final boolean zzac(zzegu zzegu) {
        return this.zznkx.zzb(zzegu, zznku) != null;
    }

    public final long zzcad() {
        return (long) zza(zznkv).size();
    }

    public final void zzg(zzelu zzelu) {
        zzb(zzelu, true);
    }

    public final void zzh(zzelu zzelu) {
        zzb(zzelu, false);
    }

    public final zzeko zzk(zzelu zzelu) {
        zzelu zzj = zzj(zzelu);
        Map zzaj = this.zznkx.zzaj(zzj.zzbvh());
        if (zzaj != null) {
            return (zzeko) zzaj.get(zzj.zzcbh());
        }
        return null;
    }

    public final void zzl(zzelu zzelu) {
        zzeko zzk = zzk(zzj(zzelu));
        if (zzk != null && !zzk.complete) {
            zza(zzk.zzcac());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0014, code lost:
        r0 = r4.zznkx.zzaj(r5.zzbvh());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzm(com.google.android.gms.internal.zzelu r5) {
        /*
            r4 = this;
            com.google.android.gms.internal.zzegu r0 = r5.zzbvh()
            boolean r0 = r4.zzad(r0)
            r1 = 1
            if (r0 == 0) goto L_0x000c
            return r1
        L_0x000c:
            boolean r0 = r5.zzcbe()
            r2 = 0
            if (r0 == 0) goto L_0x0014
            return r2
        L_0x0014:
            com.google.android.gms.internal.zzekw<java.util.Map<com.google.android.gms.internal.zzelr, com.google.android.gms.internal.zzeko>> r0 = r4.zznkx
            com.google.android.gms.internal.zzegu r3 = r5.zzbvh()
            java.lang.Object r0 = r0.zzaj(r3)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x003b
            com.google.android.gms.internal.zzelr r3 = r5.zzcbh()
            boolean r3 = r0.containsKey(r3)
            if (r3 == 0) goto L_0x003b
            com.google.android.gms.internal.zzelr r5 = r5.zzcbh()
            java.lang.Object r5 = r0.get(r5)
            com.google.android.gms.internal.zzeko r5 = (com.google.android.gms.internal.zzeko) r5
            boolean r5 = r5.complete
            if (r5 == 0) goto L_0x003b
            return r1
        L_0x003b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzekp.zzm(com.google.android.gms.internal.zzelu):boolean");
    }

    public final void zzz(zzegu zzegu) {
        this.zznkx.zzah(zzegu).zza(new zzeku(this));
    }
}

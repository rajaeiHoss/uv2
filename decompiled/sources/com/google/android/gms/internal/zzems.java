package com.google.android.gms.internal;

import com.streamax.config.constant.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class zzems implements zzenn {
    public static Comparator<zzemq> zznoa = new zzemt();
    private final zzedq<zzemq, zzenn> zznlb;
    private final zzenn zznob;
    private String zznoc;

    protected zzems() {
        this.zznoc = null;
        this.zznlb = zzedr.zza(zznoa);
        this.zznob = zzene.zzcco();
    }

    protected zzems(zzedq<zzemq, zzenn> zzedq, zzenn zzenn) {
        this.zznoc = null;
        if (!zzedq.isEmpty() || zzenn.isEmpty()) {
            this.zznob = zzenn;
            this.zznlb = zzedq;
            return;
        }
        throw new IllegalArgumentException("Can't create empty ChildrenNode with priority!");
    }

    private static void zzb(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(" ");
        }
    }

    private final void zzc(StringBuilder sb, int i) {
        String str;
        if (!this.zznlb.isEmpty() || !this.zznob.isEmpty()) {
            sb.append("{\n");
            Iterator<Map.Entry<zzemq, zzenn>> it = this.zznlb.iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                int i2 = i + 2;
                zzb(sb, i2);
                sb.append(((zzemq) next.getKey()).asString());
                sb.append("=");
                boolean z = next.getValue() instanceof zzems;
                Object value = next.getValue();
                if (z) {
                    ((zzems) value).zzc(sb, i2);
                } else {
                    sb.append(((zzenn) value).toString());
                }
                sb.append("\n");
            }
            if (!this.zznob.isEmpty()) {
                zzb(sb, i + 2);
                sb.append(".priority=");
                sb.append(this.zznob.toString());
                sb.append("\n");
            }
            zzb(sb, i);
            str = Constants.JsonSstringSuffix;
        } else {
            str = "{ }";
        }
        sb.append(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r8) {
        /*
            r7 = this;
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            if (r8 != r7) goto L_0x0008
            return r1
        L_0x0008:
            boolean r2 = r8 instanceof com.google.android.gms.internal.zzems
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            com.google.android.gms.internal.zzems r8 = (com.google.android.gms.internal.zzems) r8
            com.google.android.gms.internal.zzenn r2 = r7.zzcce()
            com.google.android.gms.internal.zzenn r3 = r8.zzcce()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x001e
            return r0
        L_0x001e:
            com.google.android.gms.internal.zzedq<com.google.android.gms.internal.zzemq, com.google.android.gms.internal.zzenn> r2 = r7.zznlb
            int r2 = r2.size()
            com.google.android.gms.internal.zzedq<com.google.android.gms.internal.zzemq, com.google.android.gms.internal.zzenn> r3 = r8.zznlb
            int r3 = r3.size()
            if (r2 == r3) goto L_0x002d
            return r0
        L_0x002d:
            com.google.android.gms.internal.zzedq<com.google.android.gms.internal.zzemq, com.google.android.gms.internal.zzenn> r2 = r7.zznlb
            java.util.Iterator r2 = r2.iterator()
            com.google.android.gms.internal.zzedq<com.google.android.gms.internal.zzemq, com.google.android.gms.internal.zzenn> r8 = r8.zznlb
            java.util.Iterator r8 = r8.iterator()
        L_0x0039:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0072
            boolean r3 = r8.hasNext()
            if (r3 == 0) goto L_0x0072
            java.lang.Object r3 = r2.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r8.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r5 = r3.getKey()
            com.google.android.gms.internal.zzemq r5 = (com.google.android.gms.internal.zzemq) r5
            java.lang.Object r6 = r4.getKey()
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0071
            java.lang.Object r3 = r3.getValue()
            com.google.android.gms.internal.zzenn r3 = (com.google.android.gms.internal.zzenn) r3
            java.lang.Object r4 = r4.getValue()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0039
        L_0x0071:
            return r0
        L_0x0072:
            boolean r0 = r2.hasNext()
            if (r0 != 0) goto L_0x007f
            boolean r8 = r8.hasNext()
            if (r8 != 0) goto L_0x007f
            return r1
        L_0x007f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "Something went wrong internally."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzems.equals(java.lang.Object):boolean");
    }

    public int getChildCount() {
        return this.zznlb.size();
    }

    public Object getValue() {
        return getValue(false);
    }

    public Object getValue(boolean z) {
        Integer zzqm;
        if (isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<Map.Entry<zzemq, zzenn>> it = this.zznlb.iterator();
        int i = 0;
        boolean z2 = true;
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry next = it.next();
            String asString = ((zzemq) next.getKey()).asString();
            hashMap.put(asString, ((zzenn) next.getValue()).getValue(z));
            i++;
            if (z2) {
                if ((asString.length() > 1 && asString.charAt(0) == '0') || (zzqm = zzepd.zzqm(asString)) == null || zzqm.intValue() < 0) {
                    z2 = false;
                } else if (zzqm.intValue() > i2) {
                    i2 = zzqm.intValue();
                }
            }
        }
        if (z || !z2 || i2 >= i * 2) {
            if (z && !this.zznob.isEmpty()) {
                hashMap.put(".priority", this.zznob.getValue());
            }
            return hashMap;
        }
        ArrayList arrayList = new ArrayList(i2 + 1);
        for (int i3 = 0; i3 <= i2; i3++) {
            StringBuilder sb = new StringBuilder(11);
            sb.append(i3);
            arrayList.add(hashMap.get(sb.toString()));
        }
        return arrayList;
    }

    public int hashCode() {
        Iterator<zzenm> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            zzenm next = it.next();
            i = (((i * 31) + next.zzccx().hashCode()) * 17) + next.zzbve().hashCode();
        }
        return i;
    }

    public boolean isEmpty() {
        return this.zznlb.isEmpty();
    }

    public Iterator<zzenm> iterator() {
        return new zzemw(this.zznlb.iterator());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        zzc(sb, 0);
        return sb.toString();
    }

    public String zza(zzenp zzenp) {
        boolean z;
        if (zzenp == zzenp.V1) {
            StringBuilder sb = new StringBuilder();
            if (!this.zznob.isEmpty()) {
                sb.append("priority:");
                sb.append(this.zznob.zza(zzenp.V1));
                sb.append(":");
            }
            ArrayList arrayList = new ArrayList();
            Iterator<zzenm> it = iterator();
            int i = 0;
            loop0:
            while (true) {
                z = false;
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    zzenm next = it.next();
                    arrayList.add(next);
                    if (z || !next.zzbve().zzcce().isEmpty()) {
                        z = true;
                    }
                }
            }
            if (z) {
                Collections.sort(arrayList, zzens.zzccy());
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                zzenm zzenm = (zzenm) obj;
                String zzccc = zzenm.zzbve().zzccc();
                if (!zzccc.equals("")) {
                    sb.append(":");
                    sb.append(zzenm.zzccx().asString());
                    sb.append(":");
                    sb.append(zzccc);
                }
            }
            return sb.toString();
        }
        throw new IllegalArgumentException("Hashes on children nodes only supported for V1");
    }

    public final void zza(zzemv zzemv, boolean z) {
        if (!z || zzcce().isEmpty()) {
            this.zznlb.zza(zzemv);
        } else {
            this.zznlb.zza(new zzemu(this, zzemv));
        }
    }

    public zzenn zzan(zzegu zzegu) {
        zzemq zzbyq = zzegu.zzbyq();
        return zzbyq == null ? this : zzm(zzbyq).zzan(zzegu.zzbyr());
    }

    public Iterator<zzenm> zzbvr() {
        return new zzemw(this.zznlb.zzbvr());
    }

    public String zzccc() {
        if (this.zznoc == null) {
            String zza = zza(zzenp.V1);
            this.zznoc = zza.isEmpty() ? "" : zzepd.zzqk(zza);
        }
        return this.zznoc;
    }

    public boolean zzccd() {
        return false;
    }

    public zzenn zzcce() {
        return this.zznob;
    }

    public final zzemq zzccf() {
        return this.zznlb.zzbvp();
    }

    public final zzemq zzccg() {
        return this.zznlb.zzbvq();
    }

    public zzenn zze(zzemq zzemq, zzenn zzenn) {
        if (zzemq.zzcca()) {
            return zzf(zzenn);
        }
        zzedq<zzemq, zzenn> zzedq = this.zznlb;
        if (zzedq.containsKey(zzemq)) {
            zzedq = zzedq.zzbj(zzemq);
        }
        if (!zzenn.isEmpty()) {
            zzedq = zzedq.zzg(zzemq, zzenn);
        }
        return zzedq.isEmpty() ? zzene.zzcco() : new zzems(zzedq, this.zznob);
    }

    public zzenn zzf(zzenn zzenn) {
        return this.zznlb.isEmpty() ? zzene.zzcco() : new zzems(this.zznlb, zzenn);
    }

    /* renamed from: zzg */
    public int compareTo(zzenn zzenn) {
        if (isEmpty()) {
            return zzenn.isEmpty() ? 0 : -1;
        }
        if (!zzenn.zzccd() && !zzenn.isEmpty()) {
            return zzenn == zzenn.zznpf ? -1 : 0;
        }
        return 1;
    }

    public boolean zzk(zzemq zzemq) {
        return !zzm(zzemq).isEmpty();
    }

    public zzemq zzl(zzemq zzemq) {
        return this.zznlb.zzbl(zzemq);
    }

    public zzenn zzl(zzegu zzegu, zzenn zzenn) {
        zzemq zzbyq = zzegu.zzbyq();
        return zzbyq == null ? zzenn : zzbyq.zzcca() ? zzf(zzenn) : zze(zzbyq, zzm(zzbyq).zzl(zzegu.zzbyr(), zzenn));
    }

    public zzenn zzm(zzemq zzemq) {
        return (!zzemq.zzcca() || this.zznob.isEmpty()) ? this.zznlb.containsKey(zzemq) ? this.zznlb.get(zzemq) : zzene.zzcco() : this.zznob;
    }
}

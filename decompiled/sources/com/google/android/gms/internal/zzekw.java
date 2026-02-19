package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class zzekw<T> implements Iterable<Map.Entry<zzegu, T>> {
    private static final zzedq zznlc;
    private static final zzekw zznld;
    private final T value;
    private final zzedq<zzemq, zzekw<T>> zznlb;

    static {
        zzedq<zzemq, zzekw> zza = zzedr.zza(zzeek.zze(zzemq.class));
        zznlc = zza;
        zznld = new zzekw((Object) null, zza);
    }

    public zzekw(T t) {
        this(t, zznlc);
    }

    private zzekw(T t, zzedq<zzemq, zzekw<T>> zzedq) {
        this.value = t;
        this.zznlb = zzedq;
    }

    private final <R> R zza(zzegu zzegu, zzekz<? super T, R> zzekz, R r) {
        Iterator<Map.Entry<zzemq, zzekw<T>>> it = this.zznlb.iterator();
        while (it.hasNext()) {
            Map.Entry<zzemq, zzekw<T>> entry = it.next();
            r = entry.getValue().zza(zzegu.zza(entry.getKey()), zzekz, r);
        }
        T t = this.value;
        return t != null ? zzekz.zza(zzegu, t, r) : r;
    }

    public static <V> zzekw<V> zzcaf() {
        return zznld;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzekw<?> other = (zzekw<?>) obj;
        zzedq<zzemq, zzekw<T>> zzedq = this.zznlb;
        if (zzedq == null ? other.zznlb != null : !zzedq.equals(other.zznlb)) {
            return false;
        }
        T t = this.value;
        Object otherValue = other.value;
        return t == null ? otherValue == null : t.equals(otherValue);
    }

    public final T getValue() {
        return this.value;
    }

    public final int hashCode() {
        T t = this.value;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        zzedq<zzemq, zzekw<T>> zzedq = this.zznlb;
        if (zzedq != null) {
            i = zzedq.hashCode();
        }
        return hashCode + i;
    }

    public final boolean isEmpty() {
        return this.value == null && this.zznlb.isEmpty();
    }

    public final Iterator<Map.Entry<zzegu, T>> iterator() {
        ArrayList arrayList = new ArrayList();
        zza(new zzeky(this, arrayList));
        return arrayList.iterator();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ImmutableTree { value=");
        sb.append(this.value);
        sb.append(", children={");
        Iterator<Map.Entry<zzemq, zzekw<T>>> it = this.zznlb.iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            sb.append(((zzemq) next.getKey()).asString());
            sb.append("=");
            sb.append(next.getValue());
        }
        sb.append("} }");
        return sb.toString();
    }

    public final Collection<T> values() {
        ArrayList arrayList = new ArrayList();
        zza(new zzekx(this, arrayList));
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        r0 = r4.zzbyq();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzegu zza(com.google.android.gms.internal.zzegu r4, com.google.android.gms.internal.zzela<? super T> r5) {
        /*
            r3 = this;
            T r0 = r3.value
            if (r0 == 0) goto L_0x000f
            boolean r0 = r5.zzbw(r0)
            if (r0 == 0) goto L_0x000f
            com.google.android.gms.internal.zzegu r4 = com.google.android.gms.internal.zzegu.zzbyn()
            return r4
        L_0x000f:
            boolean r0 = r4.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0017
            return r1
        L_0x0017:
            com.google.android.gms.internal.zzemq r0 = r4.zzbyq()
            com.google.android.gms.internal.zzedq<com.google.android.gms.internal.zzemq, com.google.android.gms.internal.zzekw<T>> r2 = r3.zznlb
            java.lang.Object r2 = r2.get(r0)
            com.google.android.gms.internal.zzekw r2 = (com.google.android.gms.internal.zzekw) r2
            if (r2 == 0) goto L_0x003f
            com.google.android.gms.internal.zzegu r4 = r4.zzbyr()
            com.google.android.gms.internal.zzegu r4 = r2.zza((com.google.android.gms.internal.zzegu) r4, r5)
            if (r4 == 0) goto L_0x003f
            com.google.android.gms.internal.zzegu r5 = new com.google.android.gms.internal.zzegu
            r1 = 1
            com.google.android.gms.internal.zzemq[] r1 = new com.google.android.gms.internal.zzemq[r1]
            r2 = 0
            r1[r2] = r0
            r5.<init>((com.google.android.gms.internal.zzemq[]) r1)
            com.google.android.gms.internal.zzegu r4 = r5.zzh(r4)
            return r4
        L_0x003f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzekw.zza(com.google.android.gms.internal.zzegu, com.google.android.gms.internal.zzela):com.google.android.gms.internal.zzegu");
    }

    public final zzekw<T> zza(zzegu zzegu, zzekw<T> zzekw) {
        if (zzegu.isEmpty()) {
            return zzekw;
        }
        zzemq zzbyq = zzegu.zzbyq();
        zzekw<T> zzekw2 = this.zznlb.get(zzbyq);
        if (zzekw2 == null) {
            zzekw2 = (zzekw<T>) zznld;
        }
        zzekw<T> zza = zzekw2.zza(zzegu.zzbyr(), zzekw);
        return new zzekw<>(this.value, zza.isEmpty() ? this.zznlb.zzbj(zzbyq) : this.zznlb.zzg(zzbyq, zza));
    }

    public final void zza(zzekz<T, Void> zzekz) {
        zza(zzegu.zzbyn(), zzekz, null);
    }

    public final zzegu zzaf(zzegu zzegu) {
        return zza(zzegu, zzela.zznlg);
    }

    public final T zzag(zzegu zzegu) {
        zzela<? super T> filter = (zzela<? super T>) zzela.zznlg;
        T t = this.value;
        T t2 = (t == null || !filter.zzbw(t)) ? null : this.value;
        Iterator<zzemq> it = zzegu.iterator();
        zzekw<T> zzekw = this;
        while (it.hasNext() && (zzekw = zzekw.zznlb.get(it.next())) != null) {
            T t3 = zzekw.value;
            if (t3 != null && filter.zzbw(t3)) {
                t2 = zzekw.value;
            }
        }
        return t2;
    }

    public final zzekw<T> zzah(zzegu zzegu) {
        zzekw<T> zzekw = this;
        while (!zzegu.isEmpty()) {
            zzekw = zzekw.zznlb.get(zzegu.zzbyq());
            if (zzekw == null) {
                return (zzekw<T>) zznld;
            }
            zzegu = zzegu.zzbyr();
        }
        return zzekw;
    }

    public final zzekw<T> zzai(zzegu zzegu) {
        if (zzegu.isEmpty()) {
            return this.zznlb.isEmpty() ? (zzekw<T>) zznld : new zzekw<>(null, this.zznlb);
        }
        zzemq zzbyq = zzegu.zzbyq();
        zzekw<T> zzekw = this.zznlb.get(zzbyq);
        if (zzekw == null) {
            return this;
        }
        zzekw<T> zzai = zzekw.zzai(zzegu.zzbyr());
        zzedq<zzemq, zzekw<T>> zzbj = zzai.isEmpty() ? this.zznlb.zzbj(zzbyq) : this.zznlb.zzg(zzbyq, zzai);
        return (this.value != null || !zzbj.isEmpty()) ? new zzekw<>(this.value, zzbj) : (zzekw<T>) zznld;
    }

    public final T zzaj(zzegu zzegu) {
        zzekw<T> zzekw = this;
        while (!zzegu.isEmpty()) {
            zzekw = zzekw.zznlb.get(zzegu.zzbyq());
            if (zzekw == null) {
                return null;
            }
            zzegu = zzegu.zzbyr();
        }
        return zzekw.value;
    }

    public final zzekw<T> zzb(zzegu zzegu, T t) {
        if (zzegu.isEmpty()) {
            return new zzekw<>(t, this.zznlb);
        }
        zzemq zzbyq = zzegu.zzbyq();
        zzekw<T> zzekw = this.zznlb.get(zzbyq);
        if (zzekw == null) {
            zzekw = (zzekw<T>) zznld;
        }
        return new zzekw<>(this.value, this.zznlb.zzg(zzbyq, zzekw.zzb(zzegu.zzbyr(), t)));
    }

    public final T zzb(zzegu zzegu, zzela<? super T> zzela) {
        T t = this.value;
        if (t != null && zzela.zzbw(t)) {
            return this.value;
        }
        Iterator<zzemq> it = zzegu.iterator();
        zzekw<T> zzekw = this;
        while (it.hasNext() && (zzekw = zzekw.zznlb.get(it.next())) != null) {
            T t2 = zzekw.value;
            if (t2 != null && zzela.zzbw(t2)) {
                return zzekw.value;
            }
        }
        return null;
    }

    public final <R> R zzb(R r, zzekz<? super T, R> zzekz) {
        return zza(zzegu.zzbyn(), zzekz, r);
    }

    public final boolean zzb(zzela<? super T> zzela) {
        T t = this.value;
        if (t != null && zzela.zzbw(t)) {
            return true;
        }
        Iterator<Map.Entry<zzemq, zzekw<T>>> it = this.zznlb.iterator();
        while (it.hasNext()) {
            if (it.next().getValue().zzb(zzela)) {
                return true;
            }
        }
        return false;
    }

    public final zzedq<zzemq, zzekw<T>> zzcag() {
        return this.zznlb;
    }

    public final zzekw<T> zze(zzemq zzemq) {
        zzekw<T> zzekw = this.zznlb.get(zzemq);
        return zzekw != null ? zzekw : (zzekw<T>) zznld;
    }
}

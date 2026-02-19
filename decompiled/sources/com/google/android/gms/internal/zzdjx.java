package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class zzdjx extends zzdjq<List<zzdjq<?>>> {
    private static final Map<String, zzdcp> zzlct;
    /* access modifiers changed from: private */
    public final ArrayList<zzdjq<?>> zzldc;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("concat", new zzdcs());
        hashMap.put("every", new zzdct());
        hashMap.put("filter", new zzdcu());
        hashMap.put("forEach", new zzdcv());
        hashMap.put("indexOf", new zzdcw());
        hashMap.put("hasOwnProperty", zzdeq.zzlab);
        hashMap.put("join", new zzdcx());
        hashMap.put("lastIndexOf", new zzdcy());
        hashMap.put("map", new zzdcz());
        hashMap.put("pop", new zzdda());
        hashMap.put("push", new zzddb());
        hashMap.put("reduce", new zzddc());
        hashMap.put("reduceRight", new zzddd());
        hashMap.put("reverse", new zzdde());
        hashMap.put("shift", new zzddf());
        hashMap.put("slice", new zzddg());
        hashMap.put("some", new zzddh());
        hashMap.put("sort", new zzddi());
        hashMap.put("splice", new zzddm());
        hashMap.put("toString", new zzdfs());
        hashMap.put("unshift", new zzddn());
        zzlct = Collections.unmodifiableMap(hashMap);
    }

    public zzdjx(List<zzdjq<?>> list) {
        zzbq.checkNotNull(list);
        this.zzldc = new ArrayList<>(list);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdjx)) {
            return false;
        }
        List list = (List) ((zzdjx) obj).value();
        if (this.zzldc.size() != list.size()) {
            return false;
        }
        boolean z = true;
        for (int i = 0; i < this.zzldc.size(); i++) {
            z = this.zzldc.get(i) == null ? list.get(i) == null : this.zzldc.get(i).equals(list.get(i));
            if (!z) {
                break;
            }
        }
        return z;
    }

    public final void setSize(int i) {
        zzbq.checkArgument(i >= 0, "Invalid array length");
        if (this.zzldc.size() != i) {
            if (this.zzldc.size() < i) {
                this.zzldc.ensureCapacity(i);
                for (int size = this.zzldc.size(); size < i; size++) {
                    this.zzldc.add((zzdjq<?>) null);
                }
                return;
            }
            ArrayList<zzdjq<?>> arrayList = this.zzldc;
            arrayList.subList(i, arrayList.size()).clear();
        }
    }

    public final String toString() {
        return this.zzldc.toString();
    }

    public final List<zzdjq<?>> value() {
        return this.zzldc;
    }

    public final void zza(int i, zzdjq<?> zzdjq) {
        if (i >= 0) {
            if (i >= this.zzldc.size()) {
                setSize(i + 1);
            }
            this.zzldc.set(i, zzdjq);
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final Iterator<zzdjq<?>> zzbko() {
        return new zzdjz(this, new zzdjy(this), super.zzbkp());
    }

    public final zzdjq<?> zzfh(int i) {
        if (i < 0 || i >= this.zzldc.size()) {
            return zzdjw.zzlcz;
        }
        zzdjq<?> zzdjq = this.zzldc.get(i);
        return zzdjq == null ? zzdjw.zzlcz : zzdjq;
    }

    public final boolean zzfi(int i) {
        return i >= 0 && i < this.zzldc.size() && this.zzldc.get(i) != null;
    }

    public final boolean zznk(String str) {
        return zzlct.containsKey(str);
    }

    public final zzdcp zznl(String str) {
        if (zznk(str)) {
            return zzlct.get(str);
        }
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51);
        sb.append("Native Method ");
        sb.append(str);
        sb.append(" is not defined for type ListWrapper.");
        throw new IllegalStateException(sb.toString());
    }
}

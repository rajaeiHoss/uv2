package com.google.android.gms.internal;

import com.google.firebase.database.DatabaseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class zzegu implements Comparable<zzegu>, Iterable<zzemq> {
    private static final zzegu zznev = new zzegu("");
    /* access modifiers changed from: private */
    public final int end;
    /* access modifiers changed from: private */
    public final int start;
    /* access modifiers changed from: private */
    public final zzemq[] zzneu;

    public zzegu(String str) {
        String[] split = str.split("/");
        int i = 0;
        for (String length : split) {
            if (length.length() > 0) {
                i++;
            }
        }
        this.zzneu = new zzemq[i];
        int i2 = 0;
        for (String str2 : split) {
            if (str2.length() > 0) {
                this.zzneu[i2] = zzemq.zzqf(str2);
                i2++;
            }
        }
        this.start = 0;
        this.end = this.zzneu.length;
    }

    public zzegu(List<String> list) {
        this.zzneu = new zzemq[list.size()];
        int i = 0;
        for (String zzqf : list) {
            this.zzneu[i] = zzemq.zzqf(zzqf);
            i++;
        }
        this.start = 0;
        this.end = list.size();
    }

    public zzegu(zzemq... zzemqArr) {
        this.zzneu = (zzemq[]) Arrays.copyOf(zzemqArr, zzemqArr.length);
        this.start = 0;
        this.end = zzemqArr.length;
    }

    private zzegu(zzemq[] zzemqArr, int i, int i2) {
        this.zzneu = zzemqArr;
        this.start = i;
        this.end = i2;
    }

    public static zzegu zza(zzegu zzegu, zzegu zzegu2) {
        while (true) {
            zzemq zzbyq = zzegu.zzbyq();
            zzemq zzbyq2 = zzegu2.zzbyq();
            if (zzbyq == null) {
                return zzegu2;
            }
            if (zzbyq.equals(zzbyq2)) {
                zzegu = zzegu.zzbyr();
                zzegu2 = zzegu2.zzbyr();
            } else {
                String valueOf = String.valueOf(zzegu2);
                String valueOf2 = String.valueOf(zzegu);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 37 + String.valueOf(valueOf2).length());
                sb.append("INTERNAL ERROR: ");
                sb.append(valueOf);
                sb.append(" is not contained in ");
                sb.append(valueOf2);
                throw new DatabaseException(sb.toString());
            }
        }
    }

    public static zzegu zzbyn() {
        return zznev;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzegu)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        zzegu zzegu = (zzegu) obj;
        if (size() != zzegu.size()) {
            return false;
        }
        int i = this.start;
        int i2 = zzegu.start;
        while (i < this.end && i2 < zzegu.end) {
            if (!this.zzneu[i].equals(zzegu.zzneu[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (int i2 = this.start; i2 < this.end; i2++) {
            i = (i * 37) + this.zzneu[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.start >= this.end;
    }

    public final Iterator<zzemq> iterator() {
        return new zzegv(this);
    }

    public final int size() {
        return this.end - this.start;
    }

    public final String toString() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            sb.append("/");
            sb.append(this.zzneu[i].asString());
        }
        return sb.toString();
    }

    public final zzegu zza(zzemq zzemq) {
        int size = size();
        int i = size + 1;
        zzemq[] zzemqArr = new zzemq[i];
        System.arraycopy(this.zzneu, this.start, zzemqArr, 0, size);
        zzemqArr[size] = zzemq;
        return new zzegu(zzemqArr, 0, i);
    }

    public final String zzbyo() {
        if (isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = this.start; i < this.end; i++) {
            if (i > this.start) {
                sb.append("/");
            }
            sb.append(this.zzneu[i].asString());
        }
        return sb.toString();
    }

    public final List<String> zzbyp() {
        ArrayList arrayList = new ArrayList(size());
        Iterator<zzemq> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().asString());
        }
        return arrayList;
    }

    public final zzemq zzbyq() {
        if (isEmpty()) {
            return null;
        }
        return this.zzneu[this.start];
    }

    public final zzegu zzbyr() {
        int i = this.start;
        if (!isEmpty()) {
            i++;
        }
        return new zzegu(this.zzneu, i, this.end);
    }

    public final zzegu zzbys() {
        if (isEmpty()) {
            return null;
        }
        return new zzegu(this.zzneu, this.start, this.end - 1);
    }

    public final zzemq zzbyt() {
        if (!isEmpty()) {
            return this.zzneu[this.end - 1];
        }
        return null;
    }

    public final zzegu zzh(zzegu zzegu) {
        int size = size() + zzegu.size();
        zzemq[] zzemqArr = new zzemq[size];
        System.arraycopy(this.zzneu, this.start, zzemqArr, 0, size());
        System.arraycopy(zzegu.zzneu, zzegu.start, zzemqArr, size(), zzegu.size());
        return new zzegu(zzemqArr, 0, size);
    }

    public final boolean zzi(zzegu zzegu) {
        if (size() > zzegu.size()) {
            return false;
        }
        int i = this.start;
        int i2 = zzegu.start;
        while (i < this.end) {
            if (!this.zzneu[i].equals(zzegu.zzneu[i2])) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0026 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* renamed from: zzj */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int compareTo(com.google.android.gms.internal.zzegu r5) {
        /*
            r4 = this;
            int r0 = r4.start
            int r1 = r5.start
        L_0x0004:
            int r2 = r4.end
            if (r0 >= r2) goto L_0x0020
            int r3 = r5.end
            if (r1 >= r3) goto L_0x0020
            com.google.android.gms.internal.zzemq[] r2 = r4.zzneu
            r2 = r2[r0]
            com.google.android.gms.internal.zzemq[] r3 = r5.zzneu
            r3 = r3[r1]
            int r2 = r2.compareTo(r3)
            if (r2 == 0) goto L_0x001b
            return r2
        L_0x001b:
            int r0 = r0 + 1
            int r1 = r1 + 1
            goto L_0x0004
        L_0x0020:
            if (r0 != r2) goto L_0x0028
            int r5 = r5.end
            if (r1 != r5) goto L_0x0028
            r5 = 0
            return r5
        L_0x0028:
            if (r0 != r2) goto L_0x002c
            r5 = -1
            return r5
        L_0x002c:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzegu.compareTo(com.google.android.gms.internal.zzegu):int");
    }
}

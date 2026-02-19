package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class zzflp implements Cloneable {
    private Object value;
    private zzfln<?, ?> zzpvr;
    private List<zzflu> zzpvs = new ArrayList();

    zzflp() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzq()];
        zza(zzflk.zzbf(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzdcm */
    public zzflp clone() {
        Object clone = null;
        zzflp zzflp = new zzflp();
        try {
            zzflp.zzpvr = this.zzpvr;
            List<zzflu> list = this.zzpvs;
            if (list == null) {
                zzflp.zzpvs = null;
            } else {
                zzflp.zzpvs.addAll(list);
            }
            Object obj = this.value;
            if (obj != null) {
                if (obj instanceof zzfls) {
                    clone = (zzfls) ((zzfls) obj).clone();
                } else if (obj instanceof byte[]) {
                    clone = ((byte[]) obj).clone();
                } else {
                    int i = 0;
                    if (obj instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) obj;
                        byte[][] bArr2 = new byte[bArr.length][];
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                        clone = bArr2;
                    } else if (obj instanceof boolean[]) {
                        clone = ((boolean[]) obj).clone();
                    } else if (obj instanceof int[]) {
                        clone = ((int[]) obj).clone();
                    } else if (obj instanceof long[]) {
                        clone = ((long[]) obj).clone();
                    } else if (obj instanceof float[]) {
                        clone = ((float[]) obj).clone();
                    } else if (obj instanceof double[]) {
                        clone = ((double[]) obj).clone();
                    } else if (obj instanceof zzfls[]) {
                        zzfls[] zzflsArr = (zzfls[]) obj;
                        zzfls[] zzflsArr2 = new zzfls[zzflsArr.length];
                        while (i < zzflsArr.length) {
                            zzflsArr2[i] = (zzfls) zzflsArr[i].clone();
                            i++;
                        }
                        clone = zzflsArr2;
                    }
                }
                if (clone != null) {
                    zzflp.value = clone;
                }
            }
            return zzflp;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        List<zzflu> list;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzflp)) {
            return false;
        }
        zzflp zzflp = (zzflp) obj;
        if (this.value == null || zzflp.value == null) {
            List<zzflu> list2 = this.zzpvs;
            if (list2 != null && (list = zzflp.zzpvs) != null) {
                return list2.equals(list);
            }
            try {
                return Arrays.equals(toByteArray(), zzflp.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            zzfln<?, ?> zzfln = this.zzpvr;
            if (zzfln != zzflp.zzpvr) {
                return false;
            }
            if (!zzfln.zznro.isArray()) {
                return this.value.equals(zzflp.value);
            }
            Object obj2 = this.value;
            return obj2 instanceof byte[] ? Arrays.equals((byte[]) obj2, (byte[]) zzflp.value) : obj2 instanceof int[] ? Arrays.equals((int[]) obj2, (int[]) zzflp.value) : obj2 instanceof long[] ? Arrays.equals((long[]) obj2, (long[]) zzflp.value) : obj2 instanceof float[] ? Arrays.equals((float[]) obj2, (float[]) zzflp.value) : obj2 instanceof double[] ? Arrays.equals((double[]) obj2, (double[]) zzflp.value) : obj2 instanceof boolean[] ? Arrays.equals((boolean[]) obj2, (boolean[]) zzflp.value) : Arrays.deepEquals((Object[]) obj2, (Object[]) zzflp.value);
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzflk zzflk) throws IOException {
        Object obj = this.value;
        if (obj != null) {
            zzfln<?, ?> zzfln = this.zzpvr;
            if (zzfln.zzpvm) {
                int length = Array.getLength(obj);
                for (int i = 0; i < length; i++) {
                    Object obj2 = Array.get(obj, i);
                    if (obj2 != null) {
                        zzfln.zza(obj2, zzflk);
                    }
                }
                return;
            }
            zzfln.zza(obj, zzflk);
            return;
        }
        for (zzflu next : this.zzpvs) {
            zzflk.zzmy(next.tag);
            zzflk.zzbh(next.zzjwl);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzflu zzflu) {
        this.zzpvs.add(zzflu);
    }

    /* access modifiers changed from: package-private */
    public final <T> T zzb(zzfln<?, T> zzfln) {
        if (this.value == null) {
            this.zzpvr = zzfln;
            this.value = zzfln.zzbq(this.zzpvs);
            this.zzpvs = null;
        } else if (!this.zzpvr.equals(zzfln)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return (T) this.value;
    }

    /* access modifiers changed from: package-private */
    public final int zzq() {
        Object obj = this.value;
        if (obj != null) {
            zzfln<?, ?> zzfln = this.zzpvr;
            if (!zzfln.zzpvm) {
                return zzfln.zzcw(obj);
            }
            int length = Array.getLength(obj);
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                if (Array.get(obj, i2) != null) {
                    i += zzfln.zzcw(Array.get(obj, i2));
                }
            }
            return i;
        }
        int i3 = 0;
        for (zzflu next : this.zzpvs) {
            i3 += zzflk.zzmf(next.tag) + 0 + next.zzjwl.length;
        }
        return i3;
    }
}

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

@zzabh
public final class zzajn {
    private final String[] zzdgp;
    private final double[] zzdgq;
    private final double[] zzdgr;
    private final int[] zzdgs;
    private int zzdgt;

    zzajn(zzajq zzajq) {
        int size = zzajq.zzdgy.size();
        this.zzdgp = (String[]) zzajq.zzdgx.toArray(new String[size]);
        this.zzdgq = zzr(zzajq.zzdgy);
        this.zzdgr = zzr(zzajq.zzdgz);
        this.zzdgs = new int[size];
        this.zzdgt = 0;
    }

    private static double[] zzr(List<Double> list) {
        int size = list.size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public final List<zzajp> getBuckets() {
        ArrayList arrayList = new ArrayList(this.zzdgp.length);
        int i = 0;
        while (true) {
            String[] strArr = this.zzdgp;
            if (i >= strArr.length) {
                return arrayList;
            }
            String str = strArr[i];
            double d = this.zzdgr[i];
            double d2 = this.zzdgq[i];
            int[] iArr = this.zzdgs;
            arrayList.add(new zzajp(str, d, d2, ((double) iArr[i]) / ((double) this.zzdgt), iArr[i]));
            i++;
        }
    }

    public final void zza(double d) {
        this.zzdgt++;
        int i = 0;
        while (true) {
            double[] dArr = this.zzdgr;
            if (i < dArr.length) {
                if (dArr[i] <= d && d < this.zzdgq[i]) {
                    int[] iArr = this.zzdgs;
                    iArr[i] = iArr[i] + 1;
                }
                if (d >= dArr[i]) {
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }
}

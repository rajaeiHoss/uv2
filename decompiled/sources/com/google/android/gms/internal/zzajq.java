package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzajq {
    /* access modifiers changed from: private */
    public final List<String> zzdgx = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzdgy = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Double> zzdgz = new ArrayList();

    public final zzajq zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzdgx.size()) {
            double doubleValue = this.zzdgz.get(i).doubleValue();
            double doubleValue2 = this.zzdgy.get(i).doubleValue();
            if (d < doubleValue || (doubleValue == d && d2 < doubleValue2)) {
                break;
            }
            i++;
        }
        this.zzdgx.add(i, str);
        this.zzdgz.add(i, Double.valueOf(d));
        this.zzdgy.add(i, Double.valueOf(d2));
        return this;
    }

    public final zzajn zzrs() {
        return new zzajn(this);
    }
}

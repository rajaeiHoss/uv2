package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzajp {
    public final int count;
    public final String name;
    private double zzdgu;
    private double zzdgv;
    public final double zzdgw;

    public zzajp(String str, double d, double d2, double d3, int i) {
        this.name = str;
        this.zzdgv = d;
        this.zzdgu = d2;
        this.zzdgw = d3;
        this.count = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzajp)) {
            return false;
        }
        zzajp zzajp = (zzajp) obj;
        return zzbg.equal(this.name, zzajp.name) && this.zzdgu == zzajp.zzdgu && this.zzdgv == zzajp.zzdgv && this.count == zzajp.count && Double.compare(this.zzdgw, zzajp.zzdgw) == 0;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.name, Double.valueOf(this.zzdgu), Double.valueOf(this.zzdgv), Double.valueOf(this.zzdgw), Integer.valueOf(this.count)});
    }

    public final String toString() {
        return zzbg.zzx(this).zzg("name", this.name).zzg("minBound", Double.valueOf(this.zzdgv)).zzg("maxBound", Double.valueOf(this.zzdgu)).zzg("percent", Double.valueOf(this.zzdgw)).zzg("count", Integer.valueOf(this.count)).toString();
    }
}

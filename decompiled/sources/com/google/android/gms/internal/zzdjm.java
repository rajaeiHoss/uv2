package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzdjm {
    private String zzfli;
    private List<zzdco> zzlck = new ArrayList();

    public final zzdjm zza(zzdco zzdco) {
        this.zzlck.add(zzdco);
        return this;
    }

    public final zzdjk zzbkl() {
        return new zzdjk(this.zzfli, this.zzlck);
    }

    public final zzdjm zznh(String str) {
        this.zzfli = str;
        return this;
    }
}

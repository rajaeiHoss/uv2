package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class zzdjg {
    private final Map<String, zzdjn> zzlce = new HashMap();
    private zzdjn zzlcf;

    public final zzdjg zza(String str, zzdjn zzdjn) {
        this.zzlce.put(str, zzdjn);
        return this;
    }

    public final zzdjg zzb(zzdjn zzdjn) {
        this.zzlcf = zzdjn;
        return this;
    }

    public final zzdje zzbke() {
        return new zzdje(this.zzlce, this.zzlcf);
    }
}

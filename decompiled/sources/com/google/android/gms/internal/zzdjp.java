package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.List;

public final class zzdjp {
    private final Object mValue;
    private final List<Integer> zzlcm = new ArrayList();
    private final Integer zzlco;
    private boolean zzlcp = false;

    public zzdjp(int i, Object obj) {
        this.zzlco = Integer.valueOf(i);
        this.mValue = obj;
    }

    public final zzdjn zzbkn() {
        zzbq.checkNotNull(this.zzlco);
        zzbq.checkNotNull(this.mValue);
        return new zzdjn(this.zzlco, this.mValue, this.zzlcm, this.zzlcp);
    }

    public final zzdjp zzcc(boolean z) {
        this.zzlcp = true;
        return this;
    }

    public final zzdjp zzfg(int i) {
        this.zzlcm.add(Integer.valueOf(i));
        return this;
    }
}

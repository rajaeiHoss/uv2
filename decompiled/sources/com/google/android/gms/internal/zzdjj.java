package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzdjj {
    private final List<zzdje> zzlcg = new ArrayList();
    private final List<zzdje> zzlch = new ArrayList();
    private final List<zzdje> zzlci = new ArrayList();
    private final List<zzdje> zzlcj = new ArrayList();

    public final zzdjh zzbkj() {
        return new zzdjh(this.zzlcg, this.zzlch, this.zzlci, this.zzlcj);
    }

    public final zzdjj zzc(zzdje zzdje) {
        this.zzlcg.add(zzdje);
        return this;
    }

    public final zzdjj zzd(zzdje zzdje) {
        this.zzlch.add(zzdje);
        return this;
    }

    public final zzdjj zze(zzdje zzdje) {
        this.zzlci.add(zzdje);
        return this;
    }

    public final zzdjj zzf(zzdje zzdje) {
        this.zzlcj.add(zzdje);
        return this;
    }
}

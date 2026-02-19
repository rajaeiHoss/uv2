package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;

public final class zzejt {
    private final zzegu zznji;
    private final zzejq zznjj;

    public zzejt(zzegu zzegu, zzejq zzejq) {
        this.zznji = zzegu;
        this.zznjj = zzejq;
    }

    public final zzenm zza(zzenn zzenn, zzenm zzenm, boolean z, zzenf zzenf) {
        return this.zznjj.zza(this.zznji, zzenn, zzenm, z, zzenf);
    }

    public final zzenn zza(zzegu zzegu, zzenn zzenn, zzenn zzenn2) {
        return this.zznjj.zza(this.zznji, zzegu, zzenn, zzenn2);
    }

    public final zzenn zza(zzemq zzemq, zzelh zzelh) {
        return this.zznjj.zza(this.zznji, zzemq, zzelh);
    }

    public final zzejt zzb(zzemq zzemq) {
        return new zzejt(this.zznji.zza(zzemq), this.zznjj);
    }

    public final zzenn zzc(zzenn zzenn) {
        return this.zznjj.zza(this.zznji, zzenn, Collections.<Long>emptyList(), false);
    }

    public final zzenn zzd(zzenn zzenn) {
        return this.zznjj.zzj(this.zznji, zzenn);
    }

    public final zzenn zzu(zzegu zzegu) {
        return this.zznjj.zzu(this.zznji.zzh(zzegu));
    }
}

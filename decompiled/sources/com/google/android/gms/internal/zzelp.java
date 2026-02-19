package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzelp {
    /* access modifiers changed from: private */
    public final zzemm zzmxz;
    private final zzegt zznei;

    public zzelp(zzegm zzegm) {
        this.zznei = zzegm.zzbyj();
        this.zzmxz = zzegm.zzqb("EventRaiser");
    }

    public final void zzay(List<? extends zzell> list) {
        if (this.zzmxz.zzcbu()) {
            zzemm zzemm = this.zzmxz;
            int size = list.size();
            StringBuilder sb = new StringBuilder(28);
            sb.append("Raising ");
            sb.append(size);
            sb.append(" event(s)");
            zzemm.zzb(sb.toString(), (Throwable) null, new Object[0]);
        }
        this.zznei.zzn(new zzelq(this, new ArrayList(list)));
    }
}

package com.google.android.gms.internal;

import java.util.List;

final class zzejr implements zzela<zzejn> {
    private /* synthetic */ boolean zznjf;
    private /* synthetic */ List zznjg;
    private /* synthetic */ zzegu zznjh;

    zzejr(zzejq zzejq, boolean z, List list, zzegu zzegu) {
        this.zznjf = z;
        this.zznjg = list;
        this.zznjh = zzegu;
    }

    public final boolean zzbw(zzejn zzejn) {
        if ((zzejn.isVisible() || this.zznjf) && !this.zznjg.contains(Long.valueOf(zzejn.zzbzh()))) {
            return zzejn.zzbvh().zzi(this.zznjh) || this.zznjh.zzi(zzejn.zzbvh());
        }
        return false;
    }
}

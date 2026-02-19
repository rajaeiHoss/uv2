package com.google.android.gms.internal;

import java.util.Iterator;

final class zzdjr implements Iterator<zzdjq<?>> {
    private /* synthetic */ Iterator zzlcr;

    zzdjr(zzdjq zzdjq, Iterator it) {
        this.zzlcr = it;
    }

    public final boolean hasNext() {
        return this.zzlcr.hasNext();
    }

    public final zzdjq<?> next() {
        return new zzdkc((String) this.zzlcr.next());
    }

    public final void remove() {
        this.zzlcr.remove();
    }
}

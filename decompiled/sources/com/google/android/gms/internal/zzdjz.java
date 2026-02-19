package com.google.android.gms.internal;

import java.util.Iterator;

final class zzdjz implements Iterator<zzdjq<?>> {
    private /* synthetic */ Iterator zzldf;
    private /* synthetic */ Iterator zzldg;

    zzdjz(zzdjx zzdjx, Iterator it, Iterator it2) {
        this.zzldf = it;
        this.zzldg = it2;
    }

    public final boolean hasNext() {
        return this.zzldf.hasNext() || this.zzldg.hasNext();
    }

    public final zzdjq<?> next() {
        return (zzdjq) (this.zzldf.hasNext() ? this.zzldf : this.zzldg).next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzdjy implements Iterator<zzdjq<?>> {
    private int zzldd = 0;
    private /* synthetic */ zzdjx zzlde;

    zzdjy(zzdjx zzdjx) {
        this.zzlde = zzdjx;
    }

    public final boolean hasNext() {
        for (int i = this.zzldd; i < this.zzlde.zzldc.size(); i++) {
            if (this.zzlde.zzldc.get(i) != null) {
                return true;
            }
        }
        return false;
    }

    public final zzdjq<?> next() {
        if (this.zzldd < this.zzlde.zzldc.size()) {
            for (int i = this.zzldd; i < this.zzlde.zzldc.size(); i++) {
                if (this.zzlde.zzldc.get(i) != null) {
                    this.zzldd = i;
                    int i2 = this.zzldd;
                    this.zzldd = i2 + 1;
                    return new zzdju(Double.valueOf((double) i2));
                }
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

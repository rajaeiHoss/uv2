package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzegv implements Iterator<zzemq> {
    private int offset = this.zznew.start;
    private /* synthetic */ zzegu zznew;

    zzegv(zzegu zzegu) {
        this.zznew = zzegu;
    }

    public final boolean hasNext() {
        return this.offset < this.zznew.end;
    }

    public final zzemq next() {
        if (hasNext()) {
            zzemq[] zzm = this.zznew.zzneu;
            int i = this.offset;
            zzemq zzemq = zzm[i];
            this.offset = i + 1;
            return zzemq;
        }
        throw new NoSuchElementException("No more elements.");
    }

    public final void remove() {
        throw new UnsupportedOperationException("Can't remove component from immutable Path!");
    }
}

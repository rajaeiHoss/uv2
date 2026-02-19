package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzdkd implements Iterator<zzdjq<?>> {
    private int zzldd = 0;
    private /* synthetic */ zzdkc zzldk;

    zzdkd(zzdkc zzdkc) {
        this.zzldk = zzdkc;
    }

    public final boolean hasNext() {
        return this.zzldd < this.zzldk.mValue.length();
    }

    public final zzdjq<?> next() {
        if (this.zzldd < this.zzldk.mValue.length()) {
            int i = this.zzldd;
            this.zzldd = i + 1;
            return new zzdju(Double.valueOf((double) i));
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}

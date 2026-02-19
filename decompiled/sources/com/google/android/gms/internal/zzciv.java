package com.google.android.gms.internal;

import java.util.Iterator;

final class zzciv implements Iterator<String> {
    private Iterator<String> zzjhz = this.zzjia.zzegt.keySet().iterator();
    private /* synthetic */ zzciu zzjia;

    zzciv(zzciu zzciu) {
        this.zzjia = zzciu;
    }

    public final boolean hasNext() {
        return this.zzjhz.hasNext();
    }

    public final String next() {
        return this.zzjhz.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

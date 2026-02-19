package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map;

final class zzemw implements Iterator<zzenm> {
    private final Iterator<Map.Entry<zzemq, zzenn>> zzmyq;

    public zzemw(Iterator<Map.Entry<zzemq, zzenn>> it) {
        this.zzmyq = it;
    }

    public final boolean hasNext() {
        return this.zzmyq.hasNext();
    }

    public final zzenm next() {
        Map.Entry<zzemq, zzenn> next = this.zzmyq.next();
        return new zzenm(next.getKey(), next.getValue());
    }

    public final void remove() {
        this.zzmyq.remove();
    }
}

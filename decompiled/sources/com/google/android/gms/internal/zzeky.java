package com.google.android.gms.internal;

import java.util.AbstractMap;
import java.util.List;

final class zzeky<T> implements zzekz<T, Void> {
    private /* synthetic */ List zznlf;

    zzeky(zzekw zzekw, List list) {
        this.zznlf = list;
    }

    public final Void zza(zzegu zzegu, T t, Void r) {
        this.zznlf.add(new AbstractMap.SimpleImmutableEntry(zzegu, t));
        return null;
    }
}

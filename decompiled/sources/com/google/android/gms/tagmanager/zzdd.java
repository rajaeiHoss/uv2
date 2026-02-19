package com.google.android.gms.tagmanager;

import android.util.LruCache;

final class zzdd<K, V> extends LruCache<K, V> {
    private final zzs<K, V> zzkqt;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzdd(zzdc<K, V> zzdc, int i, zzs<K, V> zzs) {
        super(i);
        this.zzkqt = zzs;
    }

    /* access modifiers changed from: protected */
    public final int sizeOf(K k, V v) {
        return this.zzkqt.sizeOf(k, v);
    }
}

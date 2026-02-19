package com.google.android.gms.internal;

final class zzekn<T> implements zzekz<Boolean, T> {
    private /* synthetic */ zzekz<Void, T> zznkq;

    zzekn(zzekk zzekk, zzekz<Void, T> zzekz) {
        this.zznkq = zzekz;
    }

    public final T zza(zzegu zzegu, Boolean bool, T t) {
        return !bool.booleanValue() ? this.zznkq.zza(zzegu, null, t) : t;
    }
}

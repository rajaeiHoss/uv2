package com.google.android.gms.internal;

final class zzedl implements zzekz<Void, Integer> {
    private /* synthetic */ zzekw zzmyc;

    zzedl(zzedk zzedk, zzekw zzekw) {
        this.zzmyc = zzekw;
    }

    public final Integer zza(zzegu zzegu, Void r2, Integer num) {
        return Integer.valueOf(this.zzmyc.zzaj(zzegu) == null ? num.intValue() + 1 : num.intValue());
    }
}

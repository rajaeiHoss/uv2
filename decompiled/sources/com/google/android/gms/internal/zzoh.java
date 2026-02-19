package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzoh<T> implements Callable<T> {
    private /* synthetic */ zzny<T> zzbkz;
    private /* synthetic */ zzog zzbla;

    zzoh(zzog zzog, zzny<T> zzny) {
        this.zzbla = zzog;
        this.zzbkz = zzny;
    }

    public final T call() {
        return this.zzbkz.zza(this.zzbla.zzbkx);
    }
}

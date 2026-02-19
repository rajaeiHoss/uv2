package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;

final class zzaju<T> implements zzale<zzp, T> {
    private /* synthetic */ zzajx<T> zzdhd;

    zzaju(zzajr zzajr, zzajx<T> zzajx) {
        this.zzdhd = zzajx;
    }

    public final T apply(zzp zzp) {
        return this.zzdhd.zze(new ByteArrayInputStream(zzp.data));
    }
}

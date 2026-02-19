package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;

final class zzajt<T> implements zzald<Throwable, T> {
    private /* synthetic */ zzajx<T> zzdhd;

    zzajt(zzajr zzajr, zzajx<T> zzajx) {
        this.zzdhd = zzajx;
    }

    public final zzalt<T> zzc(Throwable th) throws Exception {
        zzahw.zzb("Error occurred while dispatching http response in getter.", th);
        zzbt.zzep().zza(th, "HttpGetter.deliverResponse.1");
        return zzali.zzh(this.zzdhd.zznx());
    }
}

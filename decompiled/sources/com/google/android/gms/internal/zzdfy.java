package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.io.UnsupportedEncodingException;

public final class zzdfy extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        try {
            return new zzdkc(zzdfx.encode(zzdcq.zzd(zzdjqArr.length > 0 ? (zzdjq) zzbq.checkNotNull(zzdjqArr[0]) : zzdjw.zzlcz), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()"));
        } catch (UnsupportedEncodingException unused) {
            return zzdjw.zzlcz;
        }
    }
}

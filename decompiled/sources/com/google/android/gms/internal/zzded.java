package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import org.jivesoftware.smackx.FormField;

public final class zzded extends zzdcr {
    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 1);
        zzbq.checkArgument(!(zzdjqArr[0] instanceof zzdkb));
        zzbq.checkArgument(true ^ zzdke.zzm(zzdjqArr[0]));
        zzdjq<?> value = zzdjqArr[0];
        return new zzdkc(value == zzdjw.zzlcz ? "undefined" : value instanceof zzdjt ? FormField.TYPE_BOOLEAN : value instanceof zzdju ? "number" : value instanceof zzdkc ? "string" : value instanceof zzdjv ? "function" : "object");
    }
}

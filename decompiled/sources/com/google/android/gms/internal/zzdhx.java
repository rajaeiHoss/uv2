package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Map;

public final class zzdhx extends zzdcr {
    private final zzdaz zzkxv;
    private final zzcn zzlap;

    public zzdhx(zzcn zzcn, zzdaz zzdaz) {
        this.zzlap = zzcn;
        this.zzkxv = zzdaz;
    }

    /* access modifiers changed from: protected */
    public final zzdjq<?> zza(zzdbb zzdbb, zzdjq<?>... zzdjqArr) {
        boolean z = true;
        zzbq.checkArgument(true);
        zzbq.checkArgument(zzdjqArr.length == 1 || zzdjqArr.length == 2);
        zzbq.checkArgument(zzdjqArr[0] instanceof zzdkc);
        zzdjq<?> zzdjq = zzdjqArr.length > 1 ? zzdjqArr[1] : zzdjw.zzlcz;
        if (zzdjq != zzdjw.zzlcz && !(zzdjq instanceof zzdka)) {
            z = false;
        }
        zzbq.checkArgument(z);
        zzczu zzbjc = this.zzkxv.zzbjc();
        String str = (String) zzdjqArr[0].value();
        Bundle bundle = null;
        if (zzdjq != zzdjw.zzlcz) {
            bundle = zzdke.zzah((Map) ((zzdka) zzdjq).value());
        }
        try {
            this.zzlap.logEventInternalNoInterceptor(zzbjc.zzbiq(), str, bundle, zzbjc.currentTimeMillis());
        } catch (RemoteException e) {
            String valueOf = String.valueOf(e.getMessage());
            zzdal.e(valueOf.length() != 0 ? "Error calling measurement proxy:".concat(valueOf) : new String("Error calling measurement proxy:"));
        }
        return zzdjw.zzlcz;
    }
}

package com.google.android.gms.internal;

import java.util.Map;

final class zzeku implements zzekz<Map<zzelr, zzeko>, Void> {
    private /* synthetic */ zzekp zznla;

    zzeku(zzekp zzekp) {
        this.zznla = zzekp;
    }

    public final Void zza(zzegu zzegu, Map<zzelr, zzeko> map, Void r) {
        for (Map.Entry<zzelr, zzeko> entry : map.entrySet()) {
            zzeko zzeko = entry.getValue();
            if (!zzeko.complete) {
                this.zznla.zza(zzeko.zzcac());
            }
        }
        return null;
    }
}

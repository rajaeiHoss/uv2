package com.google.android.gms.internal;

import java.util.Map;

final class zzegk implements zzekz<zzenn, Void> {
    private /* synthetic */ Map zznec;
    private /* synthetic */ boolean zzned = true;

    zzegk(zzegi zzegi, Map map, boolean z) {
        this.zznec = map;
        this.zzned = z;
    }

    public final Void zza(zzegu zzegu, zzenn zzenn, Void r2) {
        this.zznec.put(zzegu.zzbyo(), zzenn.getValue(this.zzned));
        return null;
    }
}

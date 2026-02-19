package com.google.android.gms.internal;

import java.util.Map;

final class zzeij extends zzemv {
    private /* synthetic */ Map zznhj;
    private /* synthetic */ zzeik zznhk;

    zzeij(Map map, zzeik zzeik) {
        this.zznhj = map;
        this.zznhk = zzeik;
    }

    public final void zzh(zzemq zzemq, zzenn zzenn) {
        zzenn zza = zzeih.zza(zzenn, (Map<String, Object>) this.zznhj);
        if (zza != zzenn) {
            this.zznhk.zzg(new zzegu(zzemq.asString()), zza);
        }
    }
}

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.zzaof;
import java.util.Map;

final class zzs implements zzt<zzaof> {
    zzs() {
    }

    public final void zza(zzaof zzaof, Map<String, String> map) {
        if (map.keySet().contains("start")) {
            zzaof.zzak(true);
        }
        if (map.keySet().contains("stop")) {
            zzaof.zzak(false);
        }
    }
}
